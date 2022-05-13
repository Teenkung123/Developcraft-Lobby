package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.teenkung.devlobby.DevLobby.colorize;
import static com.teenkung.devlobby.DevLobby.colorizeArray;

public class ItemBuilderTemplate {

    private static ItemStack LobbySelector;
    private static ItemStack PlayerOption;
    private static ItemStack eGlowItem;

    public static void loadTemplate() {
        LobbySelector = new ItemBuilder(Material.CLOCK, 1).setDisplayName(colorize("&eLobby Selector &b(Right Click)")).setStringNBT("IsLobbySelectorItem", "true").build();
        PlayerOption = new ItemBuilder(Material.BOOK, 1).setDisplayName(colorize("&ePlayer Options &b(Right Click)")).setStringNBT("IsPlayerOptionItem", "true").build();
        eGlowItem = new ItemBuilder(Material.GLOWSTONE, 1).setDisplayName(colorize(DevLobby.getInstance().getConfig().getString("Language.EGlow.Name"))).setLoreByArray(DevLobby.getInstance().replaceAllinArray(new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.EGlow.Lore")), "<require>", Rank.getRankNameColorize(PlayerOptionItemBuilder.getEglowRank()), true)).setStringNBT("IsEGlowItem", "true").build();
    }

    public static ItemStack getLobbySelector() { return LobbySelector; }
    public static ItemStack getPlayerOption() { return PlayerOption; }

    public static ItemStack getEGlowItem() { return eGlowItem; }
}