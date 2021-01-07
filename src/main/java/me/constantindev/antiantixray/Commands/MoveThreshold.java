package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class MoveThreshold extends Base {
    public MoveThreshold() {
        super("MoveThreshold", new String[]{"movethreshold", "move", "m"}, "Set the radius");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a number as argument."), false);
            return;
        }
        String newmovethreshold = args[1];
        int newmovethresholdI;
        try {
            newmovethresholdI = Integer.parseInt(newmovethreshold);
        } catch (Exception ex) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a VALID number as argument."), false);
            return;
        }
        Config.movethreshhold = newmovethresholdI;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set new move threshold to " + newmovethresholdI), false);
        super.run(args);
    }
}
