package com.teenkung.devlobby.Utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class SQLManager {

    private static final HashMap<Player, SQLPlayer> PlayerData = new HashMap<>();

    public static SQLPlayer addPlayer(Player player) {
        if (!PlayerData.containsKey(player)) {
            PlayerData.put(player, new SQLPlayer(player.getUniqueId()));
        }
        return PlayerData.get(player);
    }

    public static void removePlayer(Player player) {
        PlayerData.remove(player);
    }

    public static Boolean isContains(Player player) {
        return PlayerData.containsKey(player);
    }

    public static SQLPlayer getPlayer(Player player) {
        if (!PlayerData.containsKey(player)) {
            PlayerData.put(player, new SQLPlayer(player.getUniqueId()));
        }
        return PlayerData.get(player);
    }


}
