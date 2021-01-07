package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Auto extends Base {
    public Auto() {
        super("auto", new String[]{"auto", "a"}, "Sets whether to continually scan surroundings. WARNING: Be sure to only have this enabled when not moving too much");
    }

    @Override
    public void run(String[] args) {
        Config.auto = !Config.auto;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] " + (Config.auto ? "En" : "Dis") + "abled continually scanning."), false);
        super.run(args);
    }
}
