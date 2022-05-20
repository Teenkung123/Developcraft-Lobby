package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ConfigLoader;
import com.teenkung.devlobby.Utils.SQLManager;
import com.teenkung.devlobby.Utils.SQLPlayer;
import com.teenkung.devlobby.pvp.PVPManager;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static com.teenkung.devlobby.DevLobby.colorize;

public class DeathHandler implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer == null) {
            event.setDeathMessage(colorize(ConfigLoader.getConfig().getString("PVP.Death-Message-2", "").replace("<victim>", event.getEntity().getName())));
        } else {
            event.setDeathMessage(colorize(ConfigLoader.getConfig().getString("PVP.Death-Message", "").replace("<victim>", event.getEntity().getName()). replace("<attacker>", killer.getName())));
        }
        Player player = event.getEntity();
        player.getInventory().clear();
        PVPManager.removePlayer(player);
        PlayerManager manager = GadgetsMenuAPI.getPlayerManager(player);
        manager.giveMenuSelector();
        SQLPlayer sql = SQLManager.getPlayer(player);
        sql.executeAll();
        JoinHandler.setSlot(player);
        Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> event.getEntity().teleport(ConfigLoader.getSpawnLocation()), 2);
    }

}
