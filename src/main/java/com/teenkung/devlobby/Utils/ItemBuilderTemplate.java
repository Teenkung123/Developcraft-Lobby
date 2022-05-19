package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.teenkung.devlobby.DevLobby.colorize;

public class ItemBuilderTemplate {

    private static ItemStack LobbySelector;
    private static ItemStack PlayerOption;
    private static ItemStack eGlowItem;
    private static ItemStack BuyRank;
    private static ItemStack PVPItem;

    public static void loadTemplate() {
        Bukkit.getScheduler().runTaskAsynchronously(DevLobby.getInstance(), () -> {
            LobbySelector = new ItemBuilder(Material.CLOCK, 1).setDisplayName(colorize("&eLobby Selector &b(Right Click)")).build();
            PlayerOption = new ItemBuilder(Material.BOOK, 1).setDisplayName(colorize("&ePlayer Options &b(Right Click)")).build();
            eGlowItem = new ItemBuilder(Material.GLOWSTONE, 1).setDisplayName(colorize(DevLobby.getInstance().getConfig().getString("Language.EGlow.Name"))).setLoreByArray(DevLobby.getInstance().replaceAllinArray(new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.EGlow.Lore")), "<require>", Rank.getRankNameColorize(PlayerOptionItemBuilder.getEglowRank()), true)).setStringNBT("IsEGlowItem", "true").build();
            BuyRank = new ItemBuilder(Material.ENCHANTED_BOOK, 1).setDisplayName(colorize("&eRank Shop &b(Right Click)")).build();
            PVPItem = new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName(colorize("&ePVP Mode &c(Coming Soon! Under Maintenance)")).build();
        });
    }

    public static ItemStack getLobbySelector() { return LobbySelector; }
    public static ItemStack getPlayerOption() { return PlayerOption; }
    public static ItemStack getRankShopItem() { return BuyRank; }
    public static ItemStack getEGlowItem() { return eGlowItem; }
    public static ItemStack getPVPItem() { return PVPItem; }
}
