package me.constantindev.antiantixray.Mixins;

import me.constantindev.antiantixray.AntiAntiXray;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class TickMixin {
    int rad = 5;
    long delay = 0;
    @Inject(method="tick",at=@At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (AntiAntiXray.rvn.wasPressed()) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("Refreshing blocks..."), true);
            AntiAntiXray.revealNewBlocks(rad,delay);
        }
        if (AntiAntiXray.removeBlockBeta.isPressed()) {
            assert MinecraftClient.getInstance().crosshairTarget != null;
            BlockPos b2r = ((BlockHitResult) MinecraftClient.getInstance().crosshairTarget).getBlockPos();
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.world.removeBlock(b2r,false);
        }
    }

    @Inject(method="sendChatMessage",at=@At("HEAD"),cancellable = true)
    public void sendChatMessage(String msg, CallbackInfo ci) {
        if (msg.toLowerCase().startsWith("@aax")) {
            ci.cancel();
            String[] args = msg.substring(4).trim().split(" +");
            String cmd = args[0].toLowerCase();
            switch(cmd) {
                case "setradius":
                    if (args.length < 2) {
                        assert MinecraftClient.getInstance().player != null;
                        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a number as argument."),false);
                        break;
                    }
                    String newrad = args[1];
                    int newradI;
                    try {
                        newradI = Integer.parseInt(newrad);
                    } catch (Exception ex) {
                        assert MinecraftClient.getInstance().player != null;
                        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a VALID number as argument."),false);
                        break;
                    }
                    rad = newradI;
                    assert MinecraftClient.getInstance().player != null;
                    MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set new radius to "+newradI),false);
                    break;
                case "setdelay":
                    if (args.length < 2) {
                        assert MinecraftClient.getInstance().player != null;
                        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a number as argument."),false);
                        break;
                    }
                    String newdelay = args[1];
                    long newdelayI;
                    try {
                        newdelayI = Long.parseLong(newdelay);
                    } catch (Exception ex) {
                        assert MinecraftClient.getInstance().player != null;
                        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a VALID number as argument."),false);
                        break;
                    }
                    delay = newdelayI;
                    assert MinecraftClient.getInstance().player != null;
                    MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set new delay to "+newdelayI),false);
                    break;
                default:
                    assert MinecraftClient.getInstance().player != null;
                    MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] That command kind of does not exist"),false);
                    break;
            }
        }
    }
}
