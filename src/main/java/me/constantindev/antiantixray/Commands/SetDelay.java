package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SetDelay extends Base {
    public SetDelay() {
        super("SetDelay", new String[]{"setdelay", "sdelay", "delay", "d"}, "Sets the delay between packets sent");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a number as argument."), false);
            return;
        }
        String newdelay = args[1];
        long newdelayI;
        try {
            newdelayI = Long.parseLong(newdelay);
        } catch (Exception ex) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a VALID number as argument."), false);
            return;
        }
        Config.delay = newdelayI;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set new delay to " + newdelayI), false);
        super.run(args);
    }
}
