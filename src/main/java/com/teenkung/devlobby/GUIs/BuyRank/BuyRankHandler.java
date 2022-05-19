package com.teenkung.devlobby.GUIs.BuyRank;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import com.teenkung.devlobby.Utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.teenkung.devlobby.DevLobby.colorize;

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
                Rank rank = Rank.getRank(new ItemBuilder(event.getCurrentItem()).getStringNBT("RankType"));
                if (rank == Rank.UNKNOWN) { return; }
                if (BuyRankBuilder.getAllowedBuy(rank)) {
                    Player player = (Player) event.getWhoClicked();
                    Double price = BuyRankBuilder.getBuyPrice(rank);
                    if (DevLobby.getEconomy().getBalance(Bukkit.getOfflinePlayer(player.getUniqueId())) > price) {
                        DevLobby.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()), price);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set " + Rank.getString(rank));
                        player.closeInventory();
                    } else {
                        player.sendMessage(colorize("&cYou do not have Enough money to buy this!\nrequire &e" + (price - DevLobby.getEconomy().getBalance(Bukkit.getOfflinePlayer(player.getUniqueId()))) + "&cMore"));
                    }
                }
            }
        }
    }
}
