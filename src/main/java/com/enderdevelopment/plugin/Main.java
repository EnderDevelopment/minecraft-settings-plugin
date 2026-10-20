package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");
        this.getCommand("settings").setExecutor(new SettingsUI());
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }
}