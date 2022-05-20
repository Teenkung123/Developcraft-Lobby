package com.teenkung.devlobby.pvp;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import com.teenkung.devlobby.Utils.SQLManager;
import com.teenkung.devlobby.Utils.SQLPlayer;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import me.MrGraycat.eGlow.EGlow;
import me.MrGraycat.eGlow.Manager.Interface.IEGlowPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static com.teenkung.devlobby.DevLobby.colorize;

public class PVPHandler implements Listener {

    @EventHandler
    public void inventorySwap(PlayerItemHeldEvent event) {
        if (event.getPlayer().getInventory().getContents()[event.getNewSlot()] == null) { return; }
        if (event.getPlayer().getInventory().getContents()[event.getNewSlot()].equals(ItemBuilderTemplate.getPVPItem())) {
            Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> {
                if (!event.getPlayer().getInventory().getItemInMainHand().equals(ItemBuilderTemplate.getPVPItem())) {
                    return;
                }
                event.getPlayer().sendMessage(colorize(PVPLoader.getMessage().replace("<second>", "3")));
                Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> {
                    if (!event.getPlayer().getInventory().getItemInMainHand().equals(ItemBuilderTemplate.getPVPItem())) {
                        return;
                    }
                    event.getPlayer().sendMessage(colorize(PVPLoader.getMessage().replace("<second>", "2")));
                    Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> {
                        if (!event.getPlayer().getInventory().getItemInMainHand().equals(ItemBuilderTemplate.getPVPItem())) {
                            return;
                        }
                        event.getPlayer().sendMessage(colorize(PVPLoader.getMessage().replace("<second>", "1")));
                        Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> {
                            if (!event.getPlayer().getInventory().getItemInMainHand().equals(ItemBuilderTemplate.getPVPItem())) {
                                return;
                            }

                            PVPManager.addPlayer(event.getPlayer());
                            Player player = event.getPlayer();
                            SQLPlayer sql = SQLManager.getPlayer(player);

                            player.sendMessage(colorize(PVPLoader.getMessage2()));

                            sql.forceExecuteFly(false);
                            sql.forceExecuteVanish(false);
                            sql.forceExecuteHidePlayer(false);
                            sql.forceExecuteSpeedBoost(false);
                            sql.forceExecuteJumpBoost(false);
                            EGlow.getAPI().disableGlow(EGlow.getAPI().getEGlowPlayer(player));
                            PlayerManager manager = GadgetsMenuAPI.getPlayerManager(player);
                            manager.enableFireDamage();
                            manager.enableBlockDamage();
                            manager.enableFallDamage();
                            manager.unequipAnimatedHat();
                            manager.unequipMiniature();
                            manager.unequipPet();
                            manager.unequipEmote();
                            manager.unequipMorph();

                            PlayerInventory inv = player.getInventory();

                            inv.clear();

                            inv.setHelmet(PVPLoader.getHelmet());
                            inv.setChestplate(PVPLoader.getChestPlate());
                            inv.setLeggings(PVPLoader.getLeggings());
                            inv.setBoots(PVPLoader.getBoots());

                            inv.setItem(0, PVPLoader.getSword());
                            inv.setItem(1, PVPLoader.getAxe());
                            inv.setItem(2, PVPLoader.getBow());
                            inv.setItem(3, new ItemStack(Material.GOLDEN_APPLE, 5));
                            inv.setItem(7, new ItemStack(Material.ARROW, 16));
                            inv.setItem(8, new ItemStack(Material.COOKED_BEEF, 64));

                            //player.sendMessage(PVPManager.getAllPlaying().toString());

                        }, 20);
                    }, 20);
                }, 20);
            }, 20);
        }
    }
}
