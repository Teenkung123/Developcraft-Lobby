package com.teenkung.devlobby.Handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import static com.teenkung.devlobby.DevLobby.colorize;

public class ColoredSignHandler implements Listener {

    @EventHandler
    public void onSign(SignChangeEvent event) {
        for (int i = 0 ; i < 4 ; i++) {
            event.setLine(i, colorize(event.getLine(i)));
        }
    }

}
