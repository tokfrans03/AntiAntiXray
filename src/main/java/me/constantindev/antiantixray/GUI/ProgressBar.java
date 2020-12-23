package me.constantindev.antiantixray.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;

public class ProgressBar implements Toast {

    public boolean done = false;
    public int progress = 1;
    public double todo = Math.pow(Config.rad * 2 + 1, 3);

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        manager.getGame().getTextureManager().bindTexture(TEXTURE);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        manager.drawTexture(matrices, 0, 0, 0, 0, this.getWidth(), this.getHeight());
        int width = this.getWidth() / 2 - (manager.getGame().textRenderer.getWidth("Refreshing blocks, " + round((progress / todo) * 100, 2) + "%") / 2);
        int height = this.getHeight() / 2 - manager.getGame().textRenderer.fontHeight / 2;
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Refreshing blocks, " + round((progress / todo) * 100, 2) + "%", width, height, 0xFFFFFF);
        return done ? Visibility.HIDE : Visibility.SHOW;
    }
}
