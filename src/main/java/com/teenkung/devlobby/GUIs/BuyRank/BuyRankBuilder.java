package com.teenkung.devlobby.GUIs.BuyRank;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static com.teenkung.devlobby.DevLobby.colorize;
import static com.teenkung.devlobby.DevLobby.getEconomy;

public class BuyRankBuilder {
    private static final HashMap<Rank, Boolean> AllowedBuy = new HashMap<>();
    private static final HashMap<Rank, Double> BuyPrice = new HashMap<>();
    private static final HashMap<Rank, ArrayList<String>> Description = new HashMap<>();
    private static final HashMap<Rank, Double> RealPrice = new HashMap<>();
    private static Double TopupMultiplier;

    private static String NotEnoughMoneyMessage;
    private static String EnoughMoneyMessage;
    private static String AlreadyOwnedMessage;
    private static String NotOwnedMessage;
    private static String BuyableMessage;
    private static String UnBuyableMessage;
    private static String RequirePrevious;
    private static String EstimateMoney;

    public static void loadRankBuilder() {
        Bukkit.getScheduler().runTaskAsynchronously(DevLobby.getInstance(), () -> {
            NotEnoughMoneyMessage = DevLobby.getInstance().getConfig().getString("BuyRank.Not-Enough-Money");
            EnoughMoneyMessage = DevLobby.getInstance().getConfig().getString("BuyRank.Enough-Money");
            AlreadyOwnedMessage = DevLobby.getInstance().getConfig().getString("BuyRank.Already-Own");
            NotOwnedMessage = DevLobby.getInstance().getConfig().getString("BuyRank.Not-Owned-Yet");
            BuyableMessage = DevLobby.getInstance().getConfig().getString("BuyRank.Buyable");
            UnBuyableMessage = DevLobby.getInstance().getConfig().getString("BuyRank.UnBuyable");
            RequirePrevious = DevLobby.getInstance().getConfig().getString("BuyRank.Require-Previous");
            EstimateMoney = DevLobby.getInstance().getConfig().getString("BuyRank.Estimate-Money");

            TopupMultiplier = DevLobby.getInstance().getConfig().getDouble("BuyRank.Topup-Multiplier");

            Description.put(Rank.GUARDIAN, new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("BuyRank.Guardian.Description")));
            Description.put(Rank.HERO, new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("BuyRank.Hero.Description")));
            Description.put(Rank.TITAN, new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("BuyRank.Titan.Description")));
            Description.put(Rank.DRAGON, new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("BuyRank.Dragon.Description")));
            Description.put(Rank.SUPREME, new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("BuyRank.Supreme.Description")));

            BuyPrice.put(Rank.GUARDIAN, DevLobby.getInstance().getConfig().getDouble("BuyRank.Guardian.Price"));
            BuyPrice.put(Rank.HERO, DevLobby.getInstance().getConfig().getDouble("BuyRank.Hero.Price"));
            BuyPrice.put(Rank.TITAN, DevLobby.getInstance().getConfig().getDouble("BuyRank.Titan.Price"));
            BuyPrice.put(Rank.DRAGON, DevLobby.getInstance().getConfig().getDouble("BuyRank.Dragon.Price"));
            BuyPrice.put(Rank.SUPREME, DevLobby.getInstance().getConfig().getDouble("BuyRank.Supreme.Price"));

            AllowedBuy.put(Rank.GUARDIAN, DevLobby.getInstance().getConfig().getBoolean("BuyRank.Guardian.Buyable"));
            AllowedBuy.put(Rank.HERO, DevLobby.getInstance().getConfig().getBoolean("BuyRank.Hero.Buyable"));
            AllowedBuy.put(Rank.TITAN, DevLobby.getInstance().getConfig().getBoolean("BuyRank.Titan.Buyable"));
            AllowedBuy.put(Rank.DRAGON, DevLobby.getInstance().getConfig().getBoolean("BuyRank.Dragon.Buyable"));
            AllowedBuy.put(Rank.SUPREME, DevLobby.getInstance().getConfig().getBoolean("BuyRank.Supreme.Buyable"));

            RealPrice.put(Rank.GUARDIAN, DevLobby.getInstance().getConfig().getDouble("BuyRank.Guardian.Bath"));
            RealPrice.put(Rank.HERO, DevLobby.getInstance().getConfig().getDouble("BuyRank.Hero.Bath"));
            RealPrice.put(Rank.TITAN, DevLobby.getInstance().getConfig().getDouble("BuyRank.Titan.Bath"));
            RealPrice.put(Rank.DRAGON, DevLobby.getInstance().getConfig().getDouble("BuyRank.Dragon.Bath"));
            RealPrice.put(Rank.SUPREME, DevLobby.getInstance().getConfig().getDouble("BuyRank.Supreme.Bath"));
        });
    }

    public static ItemStack buildRankItem(Player player, Rank rank) {
        ItemBuilder item;
        if (rank.equals(Rank.GUARDIAN)) {
            item = new ItemBuilder(Material.YELLOW_DYE, 1).setDisplayName(colorize("&eGuardian"));
        } else if (rank.equals(Rank.HERO)) {
            item = new ItemBuilder(Material.MAGENTA_DYE, 1).setDisplayName(colorize("&dHero"));
        } else if (rank.equals(Rank.TITAN)) {
            item = new ItemBuilder(Material.LIGHT_BLUE_DYE, 1).setDisplayName(colorize("&bTitan"));
        } else if (rank.equals(Rank.DRAGON)) {
            item = new ItemBuilder(Material.LIME_DYE, 1).setDisplayName(colorize("&aDragon"));
        } else if (rank.equals(Rank.SUPREME)) {
            item = new ItemBuilder(Material.RED_DYE, 1).setDisplayName(colorize("&cSupreme"));
        } else {
            item = new ItemBuilder(Material.STONE, 1).setDisplayName(colorize("&cUnknown"));
        }
        item.setGlowing(true);
        ArrayList<String> lore = new ArrayList<>(Description.get(rank));
        lore.add(" ");
        Bukkit.broadcastMessage(Rank.getFromPlayer(player).toString() + " " + rank);
        if (Rank.getFromPlayer(player).isHigherOrEqual(rank)) {
            lore.add(AlreadyOwnedMessage);
            return item.setLoreByArray(DevLobby.colorizeArray(lore)).setStringNBT("CancelEvent", "true").build();
        } else {
            lore.add(NotOwnedMessage);
            lore.add(EstimateMoney.replace("<bath>", String.valueOf(RealPrice.get(rank) / TopupMultiplier)).replace("<multiplier>", String.valueOf(TopupMultiplier)));
            if (AllowedBuy.get(rank)) {
                lore.add(BuyableMessage.replace("<price>", String.valueOf(BuyPrice.get(rank))));
                if (Rank.getLowerRank(rank) != Rank.getFromPlayer(player)) {
                    lore.add(RequirePrevious.replace("<rank>", Rank.getRankNameColorize(Rank.getLowerRank(rank))));
                    return item.setStringNBT("CancelEvent", "true").setLoreByArray(DevLobby.colorizeArray(lore)).build();
                } else {
                    if (getEconomy().getBalance(Bukkit.getOfflinePlayer(player.getUniqueId())) < BuyPrice.get(rank)) {
                        lore.add(NotEnoughMoneyMessage.replace("<require>", String.valueOf(BuyPrice.get(rank) - getEconomy().getBalance(Bukkit.getOfflinePlayer(player.getUniqueId())))));
                        return item.setStringNBT("CancelEvent", "true").setLoreByArray(DevLobby.colorizeArray(lore)).build();
                    } else {
                        lore.add(EnoughMoneyMessage);
                        return item.setLoreByArray(DevLobby.colorizeArray(lore)).setStringNBT("RankType", Rank.getString(rank)).build();
                    }
                }
            } else {
                lore.add(UnBuyableMessage);
                return item.setStringNBT("CancelEvent", "true").setLoreByArray(DevLobby.colorizeArray(lore)).build();
            }
        }
    }

}
