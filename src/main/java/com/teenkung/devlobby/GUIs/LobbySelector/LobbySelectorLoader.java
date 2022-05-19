package com.teenkung.devlobby.GUIs.LobbySelector;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ConfigLoader;
import com.teenkung.devlobby.Utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;

import static com.teenkung.devlobby.DevLobby.colorize;

public class LobbySelectorLoader {

    private static final HashMap<String, ItemBuilder> LobbyItem = new HashMap<>();
    private static final HashMap<String, String> LobbyConnect = new HashMap<>();
    private static final HashMap<String, String> LobbyVersion = new HashMap<>();
    private static final HashMap<String, String> LobbyIP = new HashMap<>();
    private static final HashMap<String, ArrayList<String>> LobbyLore = new HashMap<>();
    private static final HashMap<String, Integer> LobbySlot = new HashMap<>();
    private static ArrayList<String> LobbyKeys = new ArrayList<>();

    private static Sound ClickSound;
    private static Sound OpenSound;
    private static Float ClickSoundVolume;
    private static Float OpenSoundVolume;
    private static Float ClickSoundPitch;
    private static Float OpenSoundPitch;

    public static ArrayList<String> getLobbyKeys() { return LobbyKeys; }
    public static String getConnectTo(String key) { return LobbyConnect.get(key); }

    public static Sound getClickSound() { return ClickSound; }
    public static Sound getOpenSound() { return OpenSound; }

    public static Float getClockSoundVolume() { return ClickSoundVolume; }
    public static Float getOpenSoundVolume() { return OpenSoundVolume; }

    public static Float getClickSoundPitch() { return ClickSoundPitch; }
    public static Float getOpenSoundPitch() { return OpenSoundPitch; }

    public static void setupLobbyItem() {

        Bukkit.getScheduler().runTaskAsynchronously(DevLobby.getInstance(), () -> {

            ClickSound = Sound.valueOf(DevLobby.getInstance().getConfig().getString("LobbySelector.GUI.Click-Sound.Sound", "BLOCK_DISPENSER_DISPENSE"));
            OpenSound = Sound.valueOf(DevLobby.getInstance().getConfig().getString("LobbySelector.GUI.Open-Sound.Sound", "BLOCK_CHES_OPEN"));

            ClickSoundVolume = Double.valueOf(DevLobby.getInstance().getConfig().getDouble("LobbySelector.GUI.Click-Sound.Volume", 1)).floatValue();
            OpenSoundVolume = Double.valueOf(DevLobby.getInstance().getConfig().getDouble("LobbySelector.GUI.Open-Sound.Volume", 1)).floatValue();

            ClickSoundPitch = Double.valueOf(DevLobby.getInstance().getConfig().getDouble("LobbySelector.GUI.Click-Sound.Pitch", 1)).floatValue();
            OpenSoundPitch = Double.valueOf(DevLobby.getInstance().getConfig().getDouble("LobbySelector.GUI.Open-Sound.Pitch", 1)).floatValue();

            ConfigurationSection section = DevLobby.getInstance().getConfig().getConfigurationSection("LobbySelector.Items");
            if (section != null) {
                LobbyKeys = new ArrayList<>(section.getKeys(false));
                for (String key : LobbyKeys) {
                    LobbyItem.put(key, new ItemBuilder(ConfigLoader.getMaterial(section.getString(key + ".Item")), 1)
                            .setDisplayName(colorize(section.getString(key + ".Name"))).setStringNBT("ItemID", key)
                    );
                    if (section.getBoolean(key + ".Glowing")) {
                        LobbyItem.replace(key, LobbyItem.get(key).setGlowing(true));
                    }
                    LobbyConnect.put(key, section.getString(key + ".Connect"));
                    LobbyVersion.put(key, section.getString(key + ".Version"));
                    LobbyIP.put(key, section.getString(key + ".IP-Address"));
                    LobbyLore.put(key, new ArrayList<>(section.getStringList(key + ".Lore")));
                    LobbySlot.put(key, section.getInt(key + ".Slot"));
                }
            }
        });
    }

    public static void updateLobbyItem() {
        for (String key : LobbyKeys) {

            String versionLore = DevLobby.getInstance().getConfig().getString("LobbySelector.Lores.Version", "&cGame Version: &f<version>");
            String statusLore = DevLobby.getInstance().getConfig().getString("LobbySelector.Lores.Status", "&8> &fServer Status: <status>");
            String onlineLore = DevLobby.getInstance().getConfig().getString("LobbySelector.Lores.Online", "&8> &fPlayers Online: &a<online>&f/&a<max>");
            String offlineLore = DevLobby.getInstance().getConfig().getString("LobbySelector.Lores.Offline", "&8> &cCould not connect to target server due to it offline");

            String online = PlaceholderAPI.setPlaceholders(null, "%pinger_isonline_" + LobbyIP.get(key) + "%");
            String plrMax = PlaceholderAPI.setPlaceholders(null, "%pinger_max_" + LobbyIP.get(key) + "%");
            String plrOnline = PlaceholderAPI.setPlaceholders(null, "%pinger_players_" + LobbyIP.get(key) + "%");

            ArrayList<String> lore = new ArrayList<>(LobbyLore.get(key));
            lore.add(" ");
            lore.add(versionLore.replace("<version>", LobbyVersion.get(key)));
            lore.add(statusLore.replace("<status>", online));
            lore.add(" ");
            if (ChatColor.stripColor(online).equalsIgnoreCase("Offline")) {
                lore.add(offlineLore);
            } else {
                lore.add(onlineLore.replace("<max>", plrMax).replace("<online>", plrOnline));
            }

                int onlines;
            try {
                onlines = Integer.parseInt(ChatColor.stripColor(plrOnline));
            } catch(NumberFormatException e) {
                System.out.println(colorize("&eCould not load placeholder Status setting online players to 1"));
                onlines = 1;
            }
            LobbySelectorGUI.getInventory().setItem(LobbySlot.get(key), LobbyItem.get(key).setLoreByArray(DevLobby.colorizeArray(lore)).setAmount(onlines).build());
            lore.clear();
        }
    }


}
