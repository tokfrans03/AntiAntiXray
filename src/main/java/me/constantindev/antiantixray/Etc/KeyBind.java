package me.constantindev.antiantixray.Etc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

public class KeyBind {
    int kc;
    boolean flag3 = false;

    public KeyBind(int kc) {
        this.kc = kc;
    }

    public void setKeyCode(int kc) {
        this.kc = kc;
    }

    public boolean checkPressed() {
        if (MinecraftClient.getInstance().currentScreen != null) return false;
        boolean flag2 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), kc);
        if (!flag2) {
            flag3 = false;
            return false;
        }
        if (flag3) {
            return false;
        }
        flag3 = true;
        return true;
    }
}
