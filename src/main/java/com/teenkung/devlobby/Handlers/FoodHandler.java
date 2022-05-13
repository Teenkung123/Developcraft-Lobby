package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.pvp.PVPManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodHandler implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (!PVPManager.isPlaying((Player) event.getEntity())) {
            event.setCancelled(true);
        }
    }

}
