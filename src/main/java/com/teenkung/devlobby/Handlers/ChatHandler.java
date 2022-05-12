package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.DevLobby;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.teenkung.devlobby.DevLobby.colorize;

public class ChatHandler implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(colorize(DevLobby.getChat().getPlayerPrefix(event.getPlayer()) + event.getPlayer().getName() + "&f: " + event.getMessage()));
    }
}
