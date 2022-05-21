package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.pvp.PVPManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class DamageHandler implements Listener {

    @EventHandler
    public void DamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof  Player) {
            if (!PVPManager.isPlaying((Player) event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void PlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (!PVPManager.isPlaying((Player) event.getDamager())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        event.getEntity().remove();
        if (event.getEntity().getShooter() instanceof  Player && event.getHitEntity() instanceof Player) {
            if (PVPManager.isPlaying((Player) event.getEntity().getShooter()) && PVPManager.isPlaying((Player) event.getHitEntity())) {
                if ((event.getEntity().getShooter()) != null) {
                    if (((Player) event.getEntity().getShooter()).getPlayer() != ((Player) event.getHitEntity()).getPlayer()) {
                        ((Player) event.getEntity().getShooter()).getInventory().addItem(new ItemStack(Material.ARROW, 2));
                    }
                }
            }
        }
    }
}
