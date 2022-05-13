package com.teenkung.devlobby.pvp;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PVPManager {

    private static final ArrayList<Player> Playing = new ArrayList<>();

    public static void addPlayer(Player player) {
        if (!Playing.contains(player)) {
            Playing.add(player);
        }
    }

    public static void removePlayer(Player player) {
        Playing.remove(player);
    }

    public static ArrayList<Player> getAllPlaying() {
        return Playing;
    }

    public static Boolean isPlaying(Player player) {
        return Playing.contains(player);
    }
}
