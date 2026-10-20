package com.enderdevelopment.plugin;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SettingsGUIListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Settings")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            switch (event.getCurrentItem().getType()) {
                case ZOMBIE_SPAWN_EGG:
                    toggleMobSpawn(event.getWhoClicked());
                    break;
                case NAME_TAG:
                    toggleHideName(event.getWhoClicked());
                    break;
                case ENDER_PEARL:
                    toggleNightVision(event.getWhoClicked());
                    break;
            }
        }
    }

    private void toggleMobSpawn(HumanEntity humanEntity) {
        Player player = (Player) humanEntity;
        boolean mobSpawnEnabled = !player.getWorld().getGameRuleValue(GameRule.DO_MOB_SPAWNING);
        player.getWorld().setGameRule(GameRule.DO_MOB_SPAWNING, mobSpawnEnabled);
        player.sendMessage("Mob Spawn is now " + (mobSpawnEnabled ? "ON" : "OFF"));
    }

    private void toggleHideName(HumanEntity humanEntity) {
        Player player = (Player) humanEntity;
        boolean hideNameEnabled = !player.getWorld().getGameRuleValue(GameRule.HIDE_PLAYERS_INVISIBLE);
        player.getWorld().setGameRule(GameRule.HIDE_PLAYERS_INVISIBLE, hideNameEnabled);
        player.sendMessage("Hide Name When Invisible is now " + (hideNameEnabled ? "ON" : "OFF"));
    }

    private void toggleNightVision(HumanEntity humanEntity) {
        Player player = (Player) humanEntity;
        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.sendMessage("Night Vision is now OFF");
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
            player.sendMessage("Night Vision is now ON");
        }
    }
}