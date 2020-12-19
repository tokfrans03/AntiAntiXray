package me.constantindev.antiantixray;

import me.constantindev.antiantixray.Etc.Config;
import me.constantindev.antiantixray.Etc.Logger;
import me.constantindev.antiantixray.Etc.RefreshingJob;
import me.constantindev.antiantixray.Etc.Runner;
import me.constantindev.antiantixray.GUI.ProgressBar;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;

public class AntiAntiXray implements ClientModInitializer {
    public static KeyBinding rvn = new KeyBinding("key.aax.refresh", GLFW_KEY_G, "key.categories.aax");
    public static KeyBinding removeBlockBeta = new KeyBinding("key.aax.remove", GLFW_KEY_V, "key.categories.aax");
    public static List<RefreshingJob> jobs = new ArrayList<>();
    public static Thread revealNewBlocks(int rad, long delayInMS) {
        ProgressBar pbar = new ProgressBar();
        MinecraftClient.getInstance().getToastManager().add(pbar);
        RefreshingJob rfj = new RefreshingJob(new Runner(rad,delayInMS,pbar),pbar);
        jobs.add(rfj);
        return rfj.runner;
    }

    @Override
    public void onInitializeClient() {
        Logger.info("Loading and initializing AAX...");

    }
}
