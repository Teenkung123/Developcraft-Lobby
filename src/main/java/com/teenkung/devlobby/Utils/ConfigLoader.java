package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.teenkung.devlobby.DevLobby.colorize;

public class ConfigLoader {

    private static Location SpawnLocation;

    public static FileConfiguration getConfig() {
        return DevLobby.getInstance().getConfig();
    }

    public static String getPlayerJoinMessage(Rank rank) {
        if (rank.equals(Rank.GUARDIAN) || rank.equals(Rank.GUARDIANF)) {
            return getConfig().getString("Language.JoinMessages.Guardian");
        } else if (rank.equals(Rank.HERO)) {
            return getConfig().getString("Language.JoinMessages.Hero");
        } else if (rank.equals(Rank.TITAN)) {
            return getConfig().getString("Language.JoinMessages.Titan");
        } else if (rank.equals(Rank.DRAGON)) {
            return getConfig().getString("Language.JoinMessages.Dragon");
        } else if (rank.equals(Rank.SUPREME)) {
            return getConfig().getString("Language.JoinMessages.Supreme");
        } else if (rank.equals(Rank.YOUTUBER)) {
            return getConfig().getString("Language.JoinMessages.Youtuber");
        } else if (rank.equals(Rank.ADMIN)) {
            return getConfig().getString("Language.JoinMessages.Admin");
        } else {
            return "";
        }
    }

    public static String getDatabaseHost() {
        return DevLobby.getInstance().getConfig().getString("Database.Host");
    }

    public static String getDatabasePort() {
        return DevLobby.getInstance().getConfig().getString("Database.Port");
    }

    public static String getDatabaseUser() {
        return DevLobby.getInstance().getConfig().getString("Database.Username");
    }

    public static String getDatabasePassword() {
        return DevLobby.getInstance().getConfig().getString("Database.Password");
    }

    public static String getDatabaseName() {
        return DevLobby.getInstance().getConfig().getString("Database.Database");
    }

    public static Rank getFlyItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.Fly.RequireRank", "GUARDIAN"));
    }

    public static String getFlyItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.Fly.Name", "&fFly Mode");
    }

    public static ArrayList<String> getFlyItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.Fly.Lore"));
    }

    public static Rank getJoinMessageItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.JoinMessage.RequireRank"));
    }

    public static String getJoinMessageItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.JoinMessage.Name");
    }

    public static ArrayList<String> getJoinMessageItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.JoinMessage.Lore"));
    }

    public static Rank getJoinFireworkItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.JoinFirework.RequireRank"));
    }

    public static String getJoinFireworkItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.JoinFirework.Name");
    }

    public static ArrayList<String> getJoinFireworkItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.JoinFirework.Lore"));
    }

    public static Rank getVanishItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.Vanish.RequireRank"));
    }

    public static String getVanishItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.Vanish.Name");
    }

    public static ArrayList<String> getVanishItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.Vanish.Lore"));
    }

    public static Rank getHidePlayerItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.HidePlayer.RequireRank"));
    }

    public static String getHidePlayerItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.HidePlayer.Name");
    }

    public static ArrayList<String> getHidePlayerItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.HidePlayer.Lore"));
    }

    public static Rank getSpeedBoostItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.SpeedBoost.RequireRank"));
    }

    public static String getSpeedBoostItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.SpeedBoost.Name");
    }

    public static ArrayList<String> getSpeedBoostItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.SpeedBoost.Lore"));
    }

    public static Rank getJumpBoostItemRequirement() {
        return Rank.getRank(DevLobby.getInstance().getConfig().getString("Language.JumpBoost.RequireRank"));
    }

    public static String getJumpBoostItemName() {
        return DevLobby.getInstance().getConfig().getString("Language.JumpBoost.Name");
    }

    public static ArrayList<String> getJumpBoostItemLore() {
        return new ArrayList<>(DevLobby.getInstance().getConfig().getStringList("Language.JumpBoost.Lore"));
    }

    //Lobby Selector GUI Thing

    public static ArrayList<ArrayList<String>> getLobbySelectorGUILayout() {
        ArrayList<ArrayList<String>> segment1 = new ArrayList<>();
        ArrayList<String> layout = new ArrayList<>(getConfig().getStringList("LobbySelector.GUI.Layout"));
        for (String key : layout) {
            ArrayList<String> segment2 = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (key.length() - 1 < i) {
                    segment2.add("");
                } else {
                    segment2.add(String.valueOf(key.charAt(i)));
                }
            }
            //ArrayList<String> segment2 = new ArrayList<>(Arrays.asList(key.split("", 9)));
            segment1.add(segment2);
        }
        return segment1;
    }

    public static ArrayList<String> getLobbySelectorGUIKeys() {
        ConfigurationSection config = getConfig().getConfigurationSection("LobbySelector.GUI.Items");
        if (config == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(config.getKeys(false));
    }

    public static HashMap<String, ItemStack> getLobbySelectorGUIItems() {
        HashMap<String, ItemStack> returns = new HashMap<>();
        for (String key : getLobbySelectorGUIKeys()) {
            String matStr = getConfig().getString("LobbySelector.GUI.Items." + key + ".Item");
            if (matStr == null) {
                matStr = "";
            }

            Material mat = Material.getMaterial(matStr);
            if (mat == null) {
                mat = Material.AIR;
            }

            returns.put(key, new ItemBuilder(mat, 1).setDisplayName(colorize(getConfig().getString("LobbySelector.GUI.Items." + key + ".Name"))).build());
        }
        return returns;
    }

    public static Material getMaterial(String material) {
        Material mat = Material.getMaterial(material);
        return Objects.requireNonNullElse(mat, Material.STONE);
    }

    public static Location getSpawnLocation() {
        if (SpawnLocation == null) {
            Location loc = new Location(
                    Bukkit.getWorld(getConfig().getString("SpawnLocation.World", "spawn-new")),
                    getConfig().getInt("SpawnLocation.X"),
                    getConfig().getInt("SpawnLocation.Y"),
                    getConfig().getInt("SpawnLocation.Z"),
                    getConfig().getInt("SpawnLocation.Yaw"),
                    getConfig().getInt("SpawnLocation.Pitch")
            );
            SpawnLocation = loc;
            return loc;
        } else {
            return SpawnLocation;
        }
    }
}
