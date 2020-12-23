package me.constantindev.antiantixray.Commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CommandNotFound extends Base {
    public CommandNotFound() {
        super("cmdnotfound", new String[]{"cmdnotfound"}, "");
    }

    @Override
    public void run(String[] args) {
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Command not found. Please refer to help command"), false);
        super.run(args);
    }
}
