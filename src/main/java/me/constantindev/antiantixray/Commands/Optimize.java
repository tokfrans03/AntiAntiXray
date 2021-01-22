package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Optimize extends Base {
    public Optimize() {
        super("optimize", new String[]{"optimize", "o"}, "Optimizes for either diamond, redstone ore or stone");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a ore block to optimize for as argument. Currently: diamond, redstone ore or stone"), false);
            return;
        }
        String newblock = args[1];
        assert MinecraftClient.getInstance().player != null;
        switch (newblock) {
            case "diamond":
                Config.checkblocks = new Block[]{Blocks.DIAMOND_ORE};

                MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set optimization to diamond"), false);
                break;


            case "redstone":
                Config.checkblocks = new Block[]{Blocks.REDSTONE_ORE};
                MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set optimization to redstone"), false);
                break;

            case "stone":
                Config.checkblocks = new Block[]{Blocks.REDSTONE_ORE};
                MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set optimization to stone"), false);
                break;

            default:
                Config.checkblocks = new Block[]{Blocks.OBSIDIAN, Blocks.CLAY, Blocks.MOSSY_COBBLESTONE,
                        Blocks.DIAMOND_ORE, Blocks.REDSTONE_ORE, Blocks.IRON_ORE, Blocks.COAL_ORE, Blocks.LAPIS_ORE,
                        Blocks.GOLD_ORE, Blocks.EMERALD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE};
                MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] optimization reset to all ores"), false);
        }

        assert MinecraftClient.getInstance().player != null;
        super.run(args);
    }
}
