package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.pvp.PVPManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionHandler implements Listener {

    @EventHandler
    public void onInventoryInteraction(InventoryClickEvent event) {
        if (!event.getWhoClicked().isOp() && !PVPManager.isPlaying((Player) event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && !event.getPlayer().isOp()) {
            Material block = event.getClickedBlock().getType();
            if (block == Material.ANVIL) {
                event.setCancelled(true);
            } else if (block == Material.FLETCHING_TABLE) {
                event.setCancelled(true);
            } else if (block == Material.CRAFTING_TABLE) {
                event.setCancelled(true);
            } else if (block == Material.ENCHANTING_TABLE) {
                event.setCancelled(true);
            } else if (block == Material.LECTERN) {
                event.setCancelled(true);
            } else if (block == Material.LOOM) {
                event.setCancelled(true);
            } else if (block.toString().contains("POTTED")) {
                event.setCancelled(true);
            } else if (block == Material.SWEET_BERRY_BUSH) {
                event.setCancelled(true);
            } else if (block == Material.HOPPER) {
                event.setCancelled(true);
            } else if (block == Material.CHIPPED_ANVIL) {
                event.setCancelled(true);
            } else if (block == Material.DAMAGED_ANVIL) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onArmorStandInteract(PlayerArmorStandManipulateEvent event) {
        if (!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }

}
