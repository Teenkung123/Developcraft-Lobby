package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionGUI;
import com.teenkung.devlobby.Utils.SQLManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitHandler implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PlayerOptionGUI.deletePlayerOptionGUI(event.getPlayer());
        SQLManager.removePlayer(event.getPlayer());
        event.setQuitMessage(null);
    }

}
