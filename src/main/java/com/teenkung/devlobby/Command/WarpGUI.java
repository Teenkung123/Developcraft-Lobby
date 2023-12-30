package com.teenkung.devlobby.Command;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.GUIs.LobbySelector.LobbySelectorGUI;
import com.teenkung.devlobby.GUIs.LobbySelector.LobbySelectorLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpGUI implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> {
                player.playSound(player.getLocation(), LobbySelectorLoader.getOpenSound(), LobbySelectorLoader.getOpenSoundVolume(), LobbySelectorLoader.getOpenSoundPitch());
                player.openInventory(LobbySelectorGUI.getInventory());
            }, 3);
        }
        return false;
    }
}
