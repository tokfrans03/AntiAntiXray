package me.constantindev.antiantixray.Etc;

import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;

public class ConfigHelper {
    public static int getScanKBFromFile() throws IOException {
        int kc = GLFW_KEY_G;
        File f = new File(System.getProperty("user.home")+"/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath()+"/scankb.bin");
        if (!sf.exists()) {
            sf.createNewFile();
            FileWriter fw = new FileWriter(sf);
            fw.write(kc+"\n");
            fw.flush();
            fw.close();
        }
        String data = "";
        Scanner s = new Scanner(sf);
        while(s.hasNext()) {
            data += s.nextLine();
        }
        s.close();
        try {
            kc = Integer.parseInt(data);
        } catch (Exception exc) {
            sf.delete();
            System.out.println(data);
        }
        return kc;
    }
    public static int getRemoveKBFromFile() throws IOException {
        int kc = GLFW_KEY_V;
        File f = new File(System.getProperty("user.home")+"/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath()+"/rmkb.bin");
        if (!sf.exists()) {
            sf.createNewFile();
            FileWriter fw = new FileWriter(sf);
            fw.write(kc+"\n");
            fw.flush();
            fw.close();
        }
        String data = "";
        Scanner s = new Scanner(sf);
        while(s.hasNext()) {
            data += s.nextLine();
        }
        s.close();
        try {
            kc = Integer.parseInt(data);
        } catch (Exception exc) {
            sf.delete();
            System.out.println(data);
        }
        return kc;
    }
    public static void setScanKBToFile(int kb) throws IOException {
        File f = new File(System.getProperty("user.home")+"/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath()+"/scankb.bin");
        if (sf.exists()) sf.delete();
        sf.createNewFile();
        FileWriter fw = new FileWriter(sf);
        fw.write(kb+"\n");
        fw.flush();
        fw.close();
    }
    public static void setRemoveKBToFile(int kb) throws IOException {
        File f = new File(System.getProperty("user.home")+"/.aaxconfig");
        if (!f.exists()) {
            f.mkdir();
        }
        if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        File sf = new File(f.getAbsolutePath()+"/rmkb.bin");
        if (sf.exists()) sf.delete();
        sf.createNewFile();
        FileWriter fw = new FileWriter(sf);
        fw.write(kb+"\n");
        fw.flush();
        fw.close();
    }
}
