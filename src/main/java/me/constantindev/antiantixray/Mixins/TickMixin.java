package me.constantindev.antiantixray.Mixins;

import me.constantindev.antiantixray.AntiAntiXray;
import me.constantindev.antiantixray.Commands.Base;
import me.constantindev.antiantixray.Etc.Config;
import me.constantindev.antiantixray.Etc.Logger;
import me.constantindev.antiantixray.Etc.RefreshingJob;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ClientPlayerEntity.class)
public class TickMixin {

    public BlockPos old;
    public int movedblocks;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        List<RefreshingJob> nl = new ArrayList<>();
        AntiAntiXray.jobs.forEach(refreshingJob -> {
            if (!refreshingJob.progress.done) {
                nl.add(refreshingJob);
            }
        });
        AntiAntiXray.jobs = nl;
        if (AntiAntiXray.rvn.checkPressed()) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("Refreshing blocks..."), true);
            AntiAntiXray.revealNewBlocks(Config.rad, Config.delay);
        }
        if (AntiAntiXray.removeBlockBeta.checkPressed()) {
            /*
             * */
            for (int cx = -3; cx <= 3; cx++) {
                for (int cy = -3; cy <= 3; cy++) {
                    for (int cz = -3; cz <= 3; cz++) {
                        assert MinecraftClient.getInstance().crosshairTarget != null;
                        BlockPos b2r = ((BlockHitResult) MinecraftClient.getInstance().crosshairTarget).getBlockPos();

                        assert MinecraftClient.getInstance().player != null;
                        //BlockState a = MinecraftClient.getInstance().player.world.getBlockState(b2r.add(cx,cy,cz));

                        Block s = Block.getBlockFromItem(MinecraftClient.getInstance().player.inventory.getMainHandStack().getItem());
                        BlockState b = Blocks.AIR.getDefaultState();
                        if (s != null) b = s.getDefaultState();

                        MinecraftClient.getInstance().player.world.setBlockState(b2r.add(cx, cy, cz), b);
                        //MinecraftClient.getInstance().player.world.removeBlock(b2r.add(cx, cy, cz), false);
                    }
                }
            }
        }

        if (Config.auto) {
            try {
                assert MinecraftClient.getInstance().player != null;
                BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();

                if (pos != old) {
                    movedblocks++;

                    if (movedblocks > Config.movethreshhold && AntiAntiXray.jobs.size() == 0) {
                        AntiAntiXray.revealNewBlocks(Config.rad, Config.delay);
                        Logger.info("Scanning new pos: " + pos.toShortString());
                        movedblocks = 0;
                    }
                }
                old = pos;

            } catch (NullPointerException e) {
                Logger.info("Null Error");
            }
        }
    }


    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String msg, CallbackInfo ci) {
        if (msg.toLowerCase().startsWith(":")) {
            ci.cancel();
            String[] args = msg.substring(1).trim().split(" +");
            String cmd = args[0].toLowerCase();
            Base cmd2r = Config.cmdmanager.getByName(cmd);
            cmd2r.run(args);
        }
        if (msg.toLowerCase().startsWith("@aax")) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("New prefix is :"), false);
            ci.cancel();
        }
    }
}
