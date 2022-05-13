package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.Utils.SQLManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitHandler implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getFly().toString());
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getJoinMessage().toString());
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getJoinFirework().toString());
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getVanish().toString());
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getHidePlayer().toString());
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getSpeedBoost().toString());
        System.out.println(SQLManager.getPlayer(event.getPlayer()).getJumpBoost().toString());
        SQLManager.removePlayer(event.getPlayer());
        event.setQuitMessage(null);
    }

}
