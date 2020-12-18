package me.constantindev.antiantixray;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;

public class AntiAntiXray implements ClientModInitializer {
    public static KeyBinding rvn = new KeyBinding("key.aax.refresh", GLFW_KEY_G, "key.categories.aax");
    public static KeyBinding removeBlockBeta = new KeyBinding("key.aax.remove",GLFW_KEY_V,"key.categories.aax");

    public static void revealNewBlocks(int rad, long delayInMS) {
        ClientPlayNetworkHandler conn = MinecraftClient.getInstance().getNetworkHandler();
        if (conn == null) return;
        assert MinecraftClient.getInstance().player != null;
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
        new Thread(()->{
            for (int cx = -rad; cx <= rad; cx++) {
                for (int cy = -rad; cy <= rad; cy++) {
                    for (int cz = -rad; cz <= rad; cz++) {
                        PlayerActionC2SPacket packet = new PlayerActionC2SPacket(
                                PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK,
                                new BlockPos(pos.getX() + cx, pos.getY() + cy, pos.getZ() + cz),
                                Direction.UP
                        );
                        conn.sendPacket(packet);
                        try {
                            Thread.sleep(delayInMS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public static void revealNewBlocks(int rad) {
        AntiAntiXray.revealNewBlocks(rad,0);
    }

    @Override
    public void onInitializeClient() {
        Logger.info("Loading and initializing AAX...");

    }


}
