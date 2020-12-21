package me.constantindev.antiantixray.Etc;

import me.constantindev.antiantixray.GUI.ProgressBar;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;

public class Runner implements Runnable {
    boolean isRunning = true;
    long delay;
    int rad;
    ProgressBar pbar;
    public Runner(int rad, long delay, ProgressBar pbar) {
        this.rad = rad;
        this.delay = delay;
        this.pbar = pbar;
    }
    @Override
    public void run() {
        ClientPlayNetworkHandler conn = MinecraftClient.getInstance().getNetworkHandler();
        if (conn == null) return;
        assert MinecraftClient.getInstance().player != null;
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();


        // Blocks that aren't ores but still needs to be checked
        Block[] checkblocks = {Blocks.OBSIDIAN, Blocks.CLAY, Blocks.MOSSY_COBBLESTONE, Blocks.GRAVEL };

        for (int cx = -rad; cx <= rad; cx++) {
            for (int cy = -rad; cy <= rad; cy++) {
                for (int cz = -rad; cz <= rad; cz++) {
                    if (!isRunning) break;
                    pbar.progress++;
                    BlockPos currblock = new BlockPos(pos.getX() + cx, pos.getY() + cy, pos.getZ() + cz);

                    Block block = MinecraftClient.getInstance().player.world.getBlockState(currblock).getBlock();

                    boolean good = false; // cool for else man

                    // only check if block is a ore or in checkblocks (obsidian for example)
                    for (int i = 0; i < checkblocks.length; i++) {
                        if (block.equals(checkblocks[i]) || block instanceof OreBlock){
                            //Logger.info(block.toString() + " Is in checkbloks or a ore");
                            good = true;
                            break;
                        }
                    }

                    if (!good){
                        continue;
                    }


                    //Logger.info("Checking " + block.toString() + " at " + currblock.toShortString());


                    PlayerActionC2SPacket packet = new PlayerActionC2SPacket(
                            PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK,
                            currblock,
                            Direction.UP
                    );
                    conn.sendPacket(packet);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }
            }
        }
        pbar.done = true;
    }
}
