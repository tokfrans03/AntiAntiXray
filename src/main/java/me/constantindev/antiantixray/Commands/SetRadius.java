package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SetRadius extends Base {
    public SetRadius() {
        super("SetRadius", new String[]{"setradius", "sradius", "radius", "r"}, "Set the radius");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a number as argument."), false);
            return;
        }
        String newrad = args[1];
        int newradI;
        try {
            newradI = Integer.parseInt(newrad);
        } catch (Exception ex) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a VALID number as argument."), false);
            return;
        }
        Config.rad = newradI;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set new radius to " + newradI), false);
        super.run(args);
    }
}
