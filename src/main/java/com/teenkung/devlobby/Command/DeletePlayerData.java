package com.teenkung.devlobby.Command;

import com.teenkung.devlobby.DevLobby;
import litebans.api.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeletePlayerData implements CommandExecutor {
    private final ArrayList<String> list = new ArrayList<>();

    @SuppressWarnings({"", "NullableProblems"})
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.getName().equals("Teenkung123")) { sender.sendMessage(ChatColor.RED + "no no no Only Plugin creator can use this command!") ;return false; }

        if (args.length == 1) {
            String target = args[0];
            Bukkit.getScheduler().runTaskAsynchronously(DevLobby.getInstance(), () -> {
                String query = "SELECT name FROM {history} WHERE uuid=? UNION SELECT name FROM {history} WHERE ip IN (SELECT ip FROM {history} WHERE uuid=?) ORDER BY NAME DESC";
                try (PreparedStatement st = Database.get().prepareStatement(query)) {
                    st.setString(1, target);
                    st.setString(2, target);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            while (rs.next()) {
                                list.add(rs.getString("name"));
                            }
                            list.remove("Teenkung123");
                            sender.sendMessage(list.toString());
                            sender.sendMessage("found total " + list.size() + " players");
                            //dispatch(list);
                        }
                        st.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //list.clear();
            });
        } if (args[1].equalsIgnoreCase("delete")) {
            for (String plr : list) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plan db remove " + plr);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plan accept");
            }
        } if (args[1].equalsIgnoreCase("see")) {
            sender.sendMessage(list.toString());
            sender.sendMessage("Showing total " + list.size() + " players");
        } if (args[1].equalsIgnoreCase("contains")) {
            sender.sendMessage("Result = " + list.contains(args[0]));
        } if (args[1].equalsIgnoreCase("clear")) {
            list.clear();
        }

        return false;
    }

}
