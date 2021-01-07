package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ScanAll extends Base {
    public ScanAll() {
        super("ScanAll", new String[]{"sall", "scanall", "sa"}, "Sets whether to scan all blocks or only ores");
    }

    @Override
    public void run(String[] args) {
        Config.scanAll = !Config.scanAll;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] " + (Config.scanAll ? "En" : "Dis") + "abled scanning all blocks."), false);
        super.run(args);
    }
}
