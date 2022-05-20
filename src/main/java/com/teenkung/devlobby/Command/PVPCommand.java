package com.teenkung.devlobby.Command;

import com.github.sirblobman.combatlogx.api.ICombatLogX;
import com.github.sirblobman.combatlogx.api.ICombatManager;
import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Handlers.JoinHandler;
import com.teenkung.devlobby.Utils.SQLManager;
import com.teenkung.devlobby.Utils.SQLPlayer;
import com.teenkung.devlobby.pvp.PVPLoader;
import com.teenkung.devlobby.pvp.PVPManager;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.teenkung.devlobby.DevLobby.colorize;

public class PVPCommand implements CommandExecutor {

    @SuppressWarnings({"", "NullableProblems"})
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (PVPManager.isPlaying((Player) sender)) {
                Player player = (Player) sender;
                PVPManager.removePlayer(player);
                sender.sendMessage(colorize(PVPLoader.getMessage4()));
                player.getInventory().clear();
                player.setHealth(20.0);
                player.setFoodLevel(20);
                player.getInventory().clear();
                Bukkit.getScheduler().runTaskLater(DevLobby.getInstance(), () -> {
                    PlayerManager manager = GadgetsMenuAPI.getPlayerManager(player);
                    manager.giveMenuSelector();
                    SQLPlayer sql = SQLManager.getPlayer(player);
                    sql.executeAll();
                    JoinHandler.setSlot(player);
                }, 2);
            }
        }
        return false;
    }


}
