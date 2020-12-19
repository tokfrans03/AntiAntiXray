package me.constantindev.antiantixray.Etc;

import me.constantindev.antiantixray.GUI.ProgressBar;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

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
        for (int cx = -rad; cx <= rad; cx++) {
            for (int cy = -rad; cy <= rad; cy++) {
                for (int cz = -rad; cz <= rad; cz++) {
                    if (!isRunning) break;
                    pbar.progress++;
                    PlayerActionC2SPacket packet = new PlayerActionC2SPacket(
                            PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK,
                            new BlockPos(pos.getX() + cx, pos.getY() + cy, pos.getZ() + cz),
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
