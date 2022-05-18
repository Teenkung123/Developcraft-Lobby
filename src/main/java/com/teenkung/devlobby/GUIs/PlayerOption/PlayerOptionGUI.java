package com.teenkung.devlobby.GUIs.PlayerOption;

import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import com.teenkung.devlobby.Utils.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class PlayerOptionGUI {

    public static void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, "Player Options");
        for (int i = 0; i < 54; i++) {

            //Fly
            if (i == 10) {
                if (SQLManager.getPlayer(player).getFly()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getFlyItem(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getFlyItem(player, false));
                }
            }
            //Join Message
            else if (i == 12) {
                if (SQLManager.getPlayer(player).getJoinMessage()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getJoinMessageItem(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getJoinMessageItem(player, false));
                }
            }
            //Join Firework
            else if (i == 14) {
                if (SQLManager.getPlayer(player).getJoinFirework()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getJoinFireworkItem(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getJoinFireworkItem(player, false));
                }
            }
            //Vanish
            else if (i == 16) {
                if (SQLManager.getPlayer(player).getVanish()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getVanishItem(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getVanishItem(player, false));
                }
            }
            //Player Hide
            else if (i == 28) {
                if (SQLManager.getPlayer(player).getHidePlayer()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getHidePlayer(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getHidePlayer(player, false));
                }
            }
            //Speed Boost
            else if (i == 30) {
                if (SQLManager.getPlayer(player).getSpeedBoost()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getSpeedBoostItem(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getSpeedBoostItem(player, false));
                }
            }
            //Jump Boost
            else if (i == 32) {
                if (SQLManager.getPlayer(player).getJumpBoost()) {
                    inv.setItem(i, PlayerOptionItemBuilder.getJumpBoostItem(player, true));
                } else {
                    inv.setItem(i, PlayerOptionItemBuilder.getJumpBoostItem(player, false));
                }
            } else if (i == 53) {
                inv.setItem(i, ItemBuilderTemplate.getEGlowItem());
            } else {
                inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setDisplayName(" ").build());
            }
        }
        player.openInventory(inv);
    }

}
