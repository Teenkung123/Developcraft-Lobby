package com.teenkung.devlobby.GUIs.PlayerOption;

import com.teenkung.devlobby.Utils.*;
import me.MrGraycat.eGlow.GUI.Menus.EGlowMainMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerOptionEventHandler implements Listener {

    @EventHandler
    public void onGUIInteract(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Player Options")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getType().equals(Material.GLOWSTONE)) {
                    if (Rank.getFromPlayer((Player) event.getWhoClicked()).isHigherOrEqual(PlayerOptionItemBuilder.getEglowRank())) {
                        new EGlowMainMenu((Player) event.getWhoClicked()).openInventory();
                    }
                } else {
                    SQLPlayer player = SQLManager.getPlayer( (Player) event.getWhoClicked());
                    ItemBuilder builder = new ItemBuilder(event.getCurrentItem());
                    if (builder.getStringNBT("PlayerOptionID") == null) {return;}
                    if (builder.getStringNBT("PlayerOptionID").equals("Fly")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getFlyRank())) {
                            player.setFly(!player.getFly());
                        }
                    }
                    else if (builder.getStringNBT("PlayerOptionID").equals("JoinMessage")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getJoinMessageRank())) {
                            player.setJoinMessage(!player.getJoinMessage());
                        }
                    }
                    else if (builder.getStringNBT("PlayerOptionID").equals("JoinFirework")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getJoinFireworkRank())) {
                            player.setJoinFirework(!player.getJoinFirework());
                        }
                    }
                    else if (builder.getStringNBT("PlayerOptionID").equals("Vanish")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getVanishRank())) {
                            player.setVanish(!player.getVanish());
                        }
                    }
                    else if (builder.getStringNBT("PlayerOptionID").equals("HidePlayer")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getHidePlayerRank())) {
                            player.setHidePlayer(!player.getHidePlayer());
                        }
                    }
                    else if (builder.getStringNBT("PlayerOptionID").equals("SpeedBoost")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getSpeedBoostRank())) {
                            player.setSpeedBoost(!player.getSpeedBoost());
                        }
                    }
                    else if (builder.getStringNBT("PlayerOptionID").equals("JumpBoost")) {
                        if (Rank.getFromPlayer(player.getPlayer()).isHigherOrEqual(PlayerOptionItemBuilder.getJumpBoostRank())) {
                            player.setJumpBoost(!player.getJumpBoost());
                        }
                    }
                    PlayerOptionGUI.openGUI(player.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (ItemBuilderTemplate.getPlayerOption().equals(event.getPlayer().getInventory().getItemInMainHand())) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                PlayerOptionGUI.openGUI(event.getPlayer());
            }
        }
    }

}
