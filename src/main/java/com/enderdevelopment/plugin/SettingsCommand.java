package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SettingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openSettingsGUI(player);
            return true;
        }
        return false;
    }

    private void openSettingsGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Settings");

        // Mob Spawn Setting
        ItemStack mobSpawnItem = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
        ItemMeta mobSpawnMeta = mobSpawnItem.getItemMeta();
        mobSpawnMeta.setDisplayName("Mob Spawn");
        mobSpawnMeta.setLore(Arrays.asList("Click to toggle", "Current: " + (player.getWorld().getGameRuleValue(GameRule.DO_MOB_SPAWNING) ? "ON" : "OFF")));
        mobSpawnItem.setItemMeta(mobSpawnMeta);
        gui.setItem(0, mobSpawnItem);

        // Hide Name When Invisible Setting
        ItemStack hideNameItem = new ItemStack(Material.NAME_TAG);
        ItemMeta hideNameMeta = hideNameItem.getItemMeta();
        hideNameMeta.setDisplayName("Hide Name When Invisible");
        hideNameMeta.setLore(Arrays.asList("Click to toggle", "Current: " + (player.getWorld().getGameRuleValue(GameRule.HIDE_PLAYERS_INVISIBLE) ? "ON" : "OFF")));
        hideNameItem.setItemMeta(hideNameMeta);
        gui.setItem(1, hideNameItem);

        // Night Vision Setting
        ItemStack nightVisionItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta nightVisionMeta = nightVisionItem.getItemMeta();
        nightVisionMeta.setDisplayName("Night Vision");
        nightVisionMeta.setLore(Arrays.asList("Click to toggle", "Current: " + (player.hasPotionEffect(PotionEffectType.NIGHT_VISION) ? "ON" : "OFF")));
        nightVisionItem.setItemMeta(nightVisionMeta);
        gui.setItem(2, nightVisionItem);

        player.openInventory(gui);
    }
}