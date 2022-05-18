package com.teenkung.devlobby.GUIs.BuyRank;

import com.teenkung.devlobby.Utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class BuyRankGUI {

    private static final HashMap<Player, Inventory> GUIs = new HashMap<>();
    private static final ItemStack Background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setDisplayName(" ").build();

    public static void createBuyRankGUI(Player player) {


        if (!GUIs.containsKey(player)) {
            Inventory inv = Bukkit.createInventory(player, 45, "Buy Rank");

            for (int i = 0 ; i < 45 ; i++) {
                inv.setItem(i, Background);
            }

            GUIs.put(player, inv);
            player.openInventory(inv);

        } else {
            player.openInventory(GUIs.get(player));
        }

    }

}
