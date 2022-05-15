package com.teenkung.devlobby.GUIs.LobbySelector;

import com.teenkung.devlobby.Utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static com.teenkung.devlobby.DevLobby.colorize;

public class LobbySelectorGUI {

    private static Inventory LobbySelectorInventory;
    private static String InventoryName;

    public static void createLobbySelectorGUI() {
        ArrayList<ArrayList<String>> layout = ConfigLoader.getLobbySelectorGUILayout();
        HashMap<String, ItemStack> keyItems = ConfigLoader.getLobbySelectorGUIItems();
        InventoryName = ConfigLoader.getConfig().getString("LobbySelector.GUI.Name");
        Bukkit.broadcastMessage(layout.get(1).size() + " " + layout.size());
        LobbySelectorInventory = Bukkit.createInventory(null, 54, colorize(InventoryName));
        ArrayList<String> joined = new ArrayList<>();
        for (ArrayList<String> lay : layout) {
            joined.addAll(lay);
        }
        int i = 0;
        for (String key : joined) {
            if (keyItems.containsKey(key)) {
                LobbySelectorInventory.setItem(i, keyItems.get(key));
            }
            i++;
        }

    }

    public static Inventory getInventory() { return LobbySelectorInventory; }
    public static String getInventoryName() { return InventoryName; }
}
