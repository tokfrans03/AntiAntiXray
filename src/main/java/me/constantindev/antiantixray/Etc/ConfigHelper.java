package me.constantindev.antiantixray.Etc;

import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigHelper {

    public static int getScanKBFromFile() throws IOException {
        int kc = GLFW_KEY_G;
        File f = new File(MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath() + "/scankb.bin");
        if (!sf.exists()) {
            sf.createNewFile();
            FileWriter fw = new FileWriter(sf);
            fw.write(kc + "\n");
            fw.flush();
            fw.close();
        }
        StringBuilder data = new StringBuilder();
        Scanner s = new Scanner(sf);
        while (s.hasNext()) {
            data.append(s.nextLine());
        }
        s.close();
        try {
            kc = Integer.parseInt(data.toString());
        } catch (Exception exc) {
            sf.delete();
            System.out.println(data);
        }
        return kc;
    }

    public static int getRemoveKBFromFile() throws IOException {
        int kc = GLFW_KEY_V;
        File f = new File(MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath() + "/rmkb.bin");
        if (!sf.exists()) {
            sf.createNewFile();
            FileWriter fw = new FileWriter(sf);
            fw.write(kc + "\n");
            fw.flush();
            fw.close();
        }
        StringBuilder data = new StringBuilder();
        Scanner s = new Scanner(sf);
        while (s.hasNext()) {
            data.append(s.nextLine());
        }
        s.close();
        try {
            kc = Integer.parseInt(data.toString());
        } catch (Exception exc) {
            sf.delete();
            System.out.println(data);
        }
        return kc;
    }

    public static void setScanKBToFile(int kb) throws IOException {

        File f = new File(MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath() + "/scankb.bin");
        if (sf.exists()) sf.delete();
        sf.createNewFile();
        FileWriter fw = new FileWriter(sf);
        fw.write(kb + "\n");
        fw.flush();
        fw.close();
    }

    public static void setRemoveKBToFile(int kb) throws IOException {
        File f = new File(MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath() + "/rmkb.bin");
        if (sf.exists()) sf.delete();
        sf.createNewFile();
        FileWriter fw = new FileWriter(sf);
        fw.write(kb + "\n");
        fw.flush();
        fw.close();
    }
}
