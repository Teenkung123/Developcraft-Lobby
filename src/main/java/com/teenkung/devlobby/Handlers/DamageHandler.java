package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.pvp.PVPManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageHandler implements Listener {

    @EventHandler
    public void DamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (!PVPManager.isPlaying(((Player) event.getEntity()).getPlayer())) {
                event.setCancelled(true);
            }
        }
    }
}
