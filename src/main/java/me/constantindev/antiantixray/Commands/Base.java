package me.constantindev.antiantixray.Commands;

public class Base {
    public final String name;
    public final String[] aliases;
    public final String description;

    public Base(String name, String[] aliases, String description) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
    }

    public void run(String[] args) {
    }
}
