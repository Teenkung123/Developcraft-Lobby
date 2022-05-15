package com.teenkung.devlobby.GUIs.LobbySelector;

import com.teenkung.devlobby.DevLobby;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessage implements PluginMessageListener {


    @SuppressWarnings("NullableProblems")
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

    }

    public static void registerPluginMessageListener() {
        DevLobby.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(DevLobby.getInstance(), "BungeeCord");
        DevLobby.getInstance().getServer().getMessenger().registerIncomingPluginChannel(DevLobby.getInstance(), "BungeeCord", new PluginMessage());
    }


}
