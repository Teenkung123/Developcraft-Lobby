package com.teenkung.devlobby.GUIs.BuyRank;

import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class BuyRankGUI {

    private static final ItemStack Background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setDisplayName(" ").build();

    public static void createBuyRankGUI(Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, "Buy Rank");

        for (int i = 0; i < 45; i++) {
            inv.setItem(i, Background);
        }
        inv.setItem(10, BuyRankBuilder.buildRankItem(player, Rank.GUARDIAN));
        inv.setItem(12, BuyRankBuilder.buildRankItem(player, Rank.HERO));
        inv.setItem(14, BuyRankBuilder.buildRankItem(player, Rank.TITAN));
        inv.setItem(16, BuyRankBuilder.buildRankItem(player, Rank.DRAGON));
        inv.setItem(31, BuyRankBuilder.buildRankItem(player, Rank.SUPREME));
        player.openInventory(inv);

    }

}
