package com.teenkung.devlobby.GUIs.LobbySelector;

import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionGUI;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbySelectorGUIHandler implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (ItemBuilderTemplate.getLobbySelector().equals(event.getPlayer().getInventory().getItemInMainHand())) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                event.getPlayer().openInventory(LobbySelectorGUI.getInventory());
            }
        }
    }

}
