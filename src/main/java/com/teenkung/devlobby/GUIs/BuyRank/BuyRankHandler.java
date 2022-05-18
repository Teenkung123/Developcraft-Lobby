package com.teenkung.devlobby.GUIs.BuyRank;

import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuyRankHandler implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (ItemBuilderTemplate.getRankShopItem().equals(event.getPlayer().getInventory().getItemInMainHand())) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                BuyRankGUI.createBuyRankGUI(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Buy Rank")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (!new ItemBuilder(event.getCurrentItem()).getStringNBT("CancelEvent").equals("true")) {

                }
            }
        }
    }
}
