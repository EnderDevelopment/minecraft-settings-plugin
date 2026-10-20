package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.GameRule;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class SettingsUI implements CommandExecutor, Listener {

    private boolean mobSpawn = true;
    private boolean hideNameWhenInvisible = true;
    private boolean nightVision = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openSettingsUI(player);
        }
        return true;
    }

    public void openSettingsUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Settings");

        // Mob Spawn Setting
        ItemStack mobSpawnItem = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
        ItemMeta mobSpawnMeta = mobSpawnItem.getItemMeta();
        mobSpawnMeta.setDisplayName("Mob Spawn");
        mobSpawnMeta.setLore(Arrays.asList("Status: " + (mobSpawn ? "On" : "Off")));
        mobSpawnItem.setItemMeta(mobSpawnMeta);
        inventory.setItem(0, mobSpawnItem);

        // Hide Name When Invisible Setting
        ItemStack hideNameItem = new ItemStack(Material.NAME_TAG);
        ItemMeta hideNameMeta = hideNameItem.getItemMeta();
        hideNameMeta.setDisplayName("Hide Name When Invisible");
        hideNameMeta.setLore(Arrays.asList("Status: " + (hideNameWhenInvisible ? "On" : "Off")));
        hideNameItem.setItemMeta(hideNameMeta);
        inventory.setItem(1, hideNameItem);

        // Night Vision Setting
        ItemStack nightVisionItem = new ItemStack(Material.GOLDEN_CARROT);
        ItemMeta nightVisionMeta = nightVisionItem.getItemMeta();
        nightVisionMeta.setDisplayName("Night Vision");
        nightVisionMeta.setLore(Arrays.asList("Status: " + (nightVision ? "On" : "Off")));
        nightVisionItem.setItemMeta(nightVisionMeta);
        inventory.setItem(2, nightVisionItem);

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Settings")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            int slot = event.getSlot();

            switch (slot) {
                case 0:
                    mobSpawn = !mobSpawn;
                    player.sendMessage("Mob Spawn: " + (mobSpawn ? "On" : "Off"));
                    break;
                case 1:
                    hideNameWhenInvisible = !hideNameWhenInvisible;
                    player.sendMessage("Hide Name When Invisible: " + (hideNameWhenInvisible ? "On" : "Off"));
                    break;
                case 2:
                    nightVision = !nightVision;
                    player.sendMessage("Night Vision: " + (nightVision ? "On" : "Off"));
                    break;
            }

            openSettingsUI(player);
        }
    }
}