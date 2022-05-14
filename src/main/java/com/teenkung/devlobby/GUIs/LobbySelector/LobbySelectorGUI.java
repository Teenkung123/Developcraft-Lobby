package com.teenkung.devlobby.GUIs.LobbySelector;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class LobbySelectorGUI {

    private static Inventory LobbySelectorInventory;

    public static void createLobbySelectorGUI() {
        ArrayList<ArrayList<String>> layout = ConfigLoader.getLobbySelectorGUILayout();
        HashMap<String, ItemStack> keyItems = ConfigLoader.getLobbySelectorGUIItems();
        LobbySelectorInventory = Bukkit.createInventory(null, layout.get(1).size() * layout.size());
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

    public static void updateLobbySelector() {
        Bukkit.getScheduler().runTaskAsynchronously(DevLobby.getInstance(), () -> {

        });
    }

    public static Inventory getInventory() { return LobbySelectorInventory; }

}
