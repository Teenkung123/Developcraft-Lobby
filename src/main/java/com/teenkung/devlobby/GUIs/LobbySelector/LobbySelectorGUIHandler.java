package com.teenkung.devlobby.GUIs.LobbySelector;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.ItemBuilder;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static com.teenkung.devlobby.DevLobby.colorize;

public class LobbySelectorGUIHandler implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (ItemBuilderTemplate.getLobbySelector().equals(event.getPlayer().getInventory().getItemInMainHand())) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                event.getPlayer().playSound(event.getPlayer().getLocation(), LobbySelectorLoader.getOpenSound(), LobbySelectorLoader.getOpenSoundVolume(), LobbySelectorLoader.getOpenSoundPitch());
                event.getPlayer().openInventory(LobbySelectorGUI.getInventory());
            }
        }
    }

    @EventHandler
    public void onInventoryInteraction(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(LobbySelectorGUI.getInventoryName())) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                String key = new ItemBuilder(event.getCurrentItem()).getStringNBT("ItemID");
                if (LobbySelectorLoader.getLobbyKeys().contains(key)) {
                    sendPlayerToServer((Player) event.getWhoClicked(), LobbySelectorLoader.getConnectTo(key));
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), LobbySelectorLoader.getClickSound(), LobbySelectorLoader.getClockSoundVolume(), LobbySelectorLoader.getClickSoundPitch());
                    event.getWhoClicked().closeInventory();

                }
            }
        }
    }
    private void sendPlayerToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(server.split(" ")[0]);
        } catch (Exception e) {
            player.sendMessage(colorize("Error while trying to connect to target server!"));
            return;
        }

        player.sendPluginMessage(DevLobby.getInstance(), "BungeeCord", b.toByteArray());
    }

}
