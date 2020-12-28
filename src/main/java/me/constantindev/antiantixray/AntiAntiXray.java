package me.constantindev.antiantixray;

import me.constantindev.antiantixray.Etc.*;
import me.constantindev.antiantixray.GUI.ProgressBar;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_M;

public class AntiAntiXray implements ClientModInitializer {
    public static KeyBind rvn = new KeyBind(Config.kcScan);
    public static KeyBind removeBlockBeta = new KeyBind(Config.kcRemove);
    public static List<RefreshingJob> jobs = new ArrayList<>();

    public static Thread revealNewBlocks(int rad, long delayInMS) {
        ProgressBar pbar = new ProgressBar();
        MinecraftClient.getInstance().getToastManager().add(pbar);
        RefreshingJob rfj = new RefreshingJob(new Runner(rad, delayInMS, pbar), pbar);
        jobs.add(rfj);
        return rfj.runner;
    }

    @Override
    public void onInitializeClient() {
        Logger.info("Loading and initializing AAX...");

    }
}
