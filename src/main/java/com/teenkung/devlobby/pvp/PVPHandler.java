package com.teenkung.devlobby.pvp;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitTask;

public class PVPHandler implements Listener {

    @EventHandler
    public void inventorySwap(PlayerItemHeldEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemBuilderTemplate.getPVPItem())) {
            Bukkit.getScheduler().runTaskTimer(DevLobby.getInstance(), () -> {
                int timer = 3;
                if (!event.getPlayer().getInventory().getItemInMainHand().equals(ItemBuilderTemplate.getPVPItem())) {
                    ;
                } else {
                    if (timer <= 0) {

                    }
                    timer--;
                }
            }, 10, 20);
        }
    }

}
