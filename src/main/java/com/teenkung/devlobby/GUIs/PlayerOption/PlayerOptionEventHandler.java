package com.teenkung.devlobby.GUIs.PlayerOption;

import com.teenkung.devlobby.Utils.ItemBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerOptionEventHandler implements Listener {

    @EventHandler
    public void onGUIInteract(InventoryClickEvent event) {

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (new ItemBuilder(event.getPlayer().getInventory().getItemInMainHand()).getStringNBT("IsPlayerOptionItem").equals("true")) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                PlayerOptionGUI.openGUI(event.getPlayer());
            }
        }
    }

}
