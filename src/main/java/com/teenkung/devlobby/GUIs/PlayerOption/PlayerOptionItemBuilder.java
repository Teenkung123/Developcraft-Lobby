package com.teenkung.devlobby.GUIs.PlayerOption;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ConfigLoader;
import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.Rank;
import com.teenkung.devlobby.Utils.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static com.teenkung.devlobby.DevLobby.colorize;

public class PlayerOptionItemBuilder {

    private static ItemBuilder FlyItem;
    private static ItemBuilder JoinMessage;
    private static ItemBuilder JoinFirework;
    private static ItemBuilder Vanish;
    private static ItemBuilder HidePlayerOff;
    private static ItemBuilder HidePlayerOn;
    private static ItemBuilder SpeedBoost;
    private static ItemBuilder JumpBoost;

    private static ArrayList<String> FlyItemLore;
    private static ArrayList<String> JoinMessageLore;
    private static ArrayList<String> JoinFireworkLore;
    private static ArrayList<String> VanishLore;
    private static ArrayList<String> HidePlayerLore;
    private static ArrayList<String> SpeedBoostLore;
    private static ArrayList<String> JumpBoostLore;

    private static Rank FlyItemRank;
    private static Rank JoinMessageRank;
    private static Rank JoinFireworkRank;
    private static Rank VanishRank;
    private static Rank HidePlayerRank;
    private static Rank SpeedBoostRank;
    private static Rank JumpBoostRank;
    private static Rank EGlowRank;


    public static void updateItemBuilder() {
        Bukkit.getScheduler().runTaskAsynchronously(DevLobby.getInstance(), () -> {
            FlyItem = new ItemBuilder(Material.FEATHER, 1).setDisplayName(colorize(ConfigLoader.getFlyItemName()));
            JoinMessage = new ItemBuilder(Material.COMMAND_BLOCK, 1).setDisplayName(colorize(ConfigLoader.getJoinMessageItemName()));
            JoinFirework = new ItemBuilder(Material.FIREWORK_ROCKET, 1).setDisplayName(colorize(ConfigLoader.getJoinFireworkItemName()));
            Vanish = new ItemBuilder(Material.GLASS_BOTTLE, 1).setDisplayName(colorize(ConfigLoader.getVanishItemName()));
            HidePlayerOff = new ItemBuilder(Material.LIME_DYE, 1).setDisplayName(colorize(ConfigLoader.getHidePlayerItemName()));
            HidePlayerOn = new ItemBuilder(Material.GRAY_DYE, 1).setDisplayName(colorize(ConfigLoader.getHidePlayerItemName()));
            SpeedBoost = new ItemBuilder(Material.SUGAR, 1).setDisplayName(colorize(ConfigLoader.getSpeedBoostItemName()));
            JumpBoost = new ItemBuilder(Material.RABBIT_FOOT, 1).setDisplayName(colorize(ConfigLoader.getJumpBoostItemName()));

            FlyItemLore = ConfigLoader.getFlyItemLore();
            JoinMessageLore = ConfigLoader.getJoinMessageItemLore();
            JoinFireworkLore = ConfigLoader.getJoinFireworkItemLore();
            VanishLore = ConfigLoader.getVanishItemLore();
            HidePlayerLore = ConfigLoader.getHidePlayerItemLore();
            SpeedBoostLore = ConfigLoader.getSpeedBoostItemLore();
            JumpBoostLore = ConfigLoader.getJumpBoostItemLore();

            FlyItemRank = ConfigLoader.getFlyItemRequirement();
            JoinMessageRank = ConfigLoader.getJoinMessageItemRequirement();
            JoinFireworkRank = ConfigLoader.getJoinFireworkItemRequirement();
            VanishRank = ConfigLoader.getVanishItemRequirement();
            HidePlayerRank = ConfigLoader.getHidePlayerItemRequirement();
            SpeedBoostRank = ConfigLoader.getSpeedBoostItemRequirement();
            JumpBoostRank = ConfigLoader.getJumpBoostItemRequirement();
            EGlowRank = Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.EGlow.RequireRank"));
        });
    }

    public static Rank getEglowRank() { return EGlowRank; }
    public static ItemStack getFlyItem(Player player,Boolean glowing) {
        if (glowing) {
            return FlyItem.setLoreByArray(DevLobby.getInstance()
                    .replaceAllinArray(DevLobby.getInstance()
                            .replaceAllinArray(FlyItemLore, "<status>", SQLManager.getPlayer(player).getFlyString(),true)
                                    ,"<require>", Rank.getRankNameColorize(FlyItemRank), true)).setStringNBT("PlayerOptionID", "Fly")
                    .setGlowing(true).build();
        } else {
            return FlyItem.setLoreByArray((DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(FlyItemLore, "<status>", SQLManager.getPlayer(player).getFlyString(),true)
                                    ,"<require>", Rank.getRankNameColorize(FlyItemRank), true))).setStringNBT("PlayerOptionID", "Fly")
                    .setGlowing(false).build();
        }
    }
    public static ItemStack getJoinMessageItem(Player player,Boolean glowing) {
        if (glowing) {
            return JoinMessage.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(JoinMessageLore, "<status>", SQLManager.getPlayer(player).getJoinMessageString(),true)
                                    ,"<require>", Rank.getRankNameColorize(JoinMessageRank), true)).setStringNBT("PlayerOptionID", "JoinMessage")
                    .setGlowing(true).build();
        } else {
            return JoinMessage.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(JoinMessageLore, "<status>", SQLManager.getPlayer(player).getJoinMessageString(),true)
                                    ,"<require>", Rank.getRankNameColorize(JoinMessageRank), true)).setStringNBT("PlayerOptionID", "JoinMessage")
                    .setGlowing(false).build();
        }
    }
    public static ItemStack getJoinFireworkItem(Player player,Boolean glowing) {
        if (glowing) {
            return JoinFirework.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(JoinFireworkLore, "<status>", SQLManager.getPlayer(player).getJoinFireworkString(),true)
                                    ,"<require>", Rank.getRankNameColorize(JoinFireworkRank), true)).setStringNBT("PlayerOptionID", "JoinFirework")
                    .setGlowing(true).build();
        } else {
            return JoinFirework.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(JoinFireworkLore, "<status>", SQLManager.getPlayer(player).getJoinFireworkString(),true)
                                    ,"<require>", Rank.getRankNameColorize(JoinFireworkRank), true)).setStringNBT("PlayerOptionID", "JoinFirework")
                    .setGlowing(false).build();
        }
    }
    public static ItemStack getVanishItem(Player player,Boolean glowing) {
        if (glowing) {
            return Vanish.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(VanishLore, "<status>", SQLManager.getPlayer(player).getVanishString(),true)
                                    ,"<require>", Rank.getRankNameColorize(VanishRank), true)).setStringNBT("PlayerOptionID", "Vanish")
                    .setGlowing(true).build();
        } else {
            return Vanish.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(VanishLore, "<status>", SQLManager.getPlayer(player).getVanishString(),true)
                                    ,"<require>", Rank.getRankNameColorize(VanishRank), true)).setStringNBT("PlayerOptionID", "Vanish")
                    .setGlowing(false).build();
        }
    }
    public static ItemStack getHidePlayer(Player player,Boolean glowing) {
        if (glowing) {
            return HidePlayerOn.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(HidePlayerLore, "<status>", SQLManager.getPlayer(player).getHidePlayerString(),true)
                                    ,"<require>", Rank.getRankNameColorize(HidePlayerRank), true)).setStringNBT("PlayerOptionID", "HidePlayer")
                    .setGlowing(false).setGlowing(true).build();
        } else {
            return HidePlayerOff.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(HidePlayerLore, "<status>", SQLManager.getPlayer(player).getHidePlayerString(),true)
                                    ,"<require>", Rank.getRankNameColorize(HidePlayerRank), true)).setStringNBT("PlayerOptionID", "HidePlayer")
                    .setGlowing(false).build();
        }
    }
    public static ItemStack getSpeedBoostItem(Player player,Boolean glowing) {
        if (glowing) {
            return SpeedBoost.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(SpeedBoostLore, "<status>", SQLManager.getPlayer(player).getSpeedBoostString(),true)
                                    ,"<require>", Rank.getRankNameColorize(SpeedBoostRank), true)).setStringNBT("PlayerOptionID", "SpeedBoost")
                    .setGlowing(true).build();
        } else {
            return SpeedBoost.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(SpeedBoostLore, "<status>", SQLManager.getPlayer(player).getSpeedBoostString(),true)
                                    ,"<require>", Rank.getRankNameColorize(SpeedBoostRank), true)).setStringNBT("PlayerOptionID", "SpeedBoost")
                    .setGlowing(false).build();
        }
    }
    public static ItemStack getJumpBoostItem(Player player,Boolean glowing) {
        if (glowing) {
            return JumpBoost.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(JumpBoostLore, "<status>", SQLManager.getPlayer(player).getJumpBoostString(),true)
                                    ,"<require>", Rank.getRankNameColorize(JumpBoostRank), true)).setStringNBT("PlayerOptionID", "JumpBoost")
                    .setGlowing(true).build();
        } else {
            return JumpBoost.setLoreByArray(DevLobby.getInstance()
                            .replaceAllinArray(DevLobby.getInstance()
                                            .replaceAllinArray(JumpBoostLore, "<status>", SQLManager.getPlayer(player).getJumpBoostString(),true)
                                    ,"<require>", Rank.getRankNameColorize(JumpBoostRank), true)).setStringNBT("PlayerOptionID", "JumpBoost")
                    .setGlowing(false).build();
        }
    }



}
