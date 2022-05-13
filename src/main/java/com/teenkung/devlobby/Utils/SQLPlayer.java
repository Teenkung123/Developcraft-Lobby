package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.teenkung.devlobby.DevLobby.colorize;

public class SQLPlayer {

    private final UUID uuid;
    private Boolean Fly;
    private Boolean JoinMessage;
    private Boolean JoinFirework;
    private Boolean Vanish;
    private Boolean HidePlayer;
    private Boolean SpeedBoost;
    private Boolean JumpBoost;

    public SQLPlayer(UUID uuid) {
        this.uuid = uuid;
        try {
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("SELECT * FROM LobbyPlayerData WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Fly = rs.getInt("FLY") == 1;
                JoinMessage = rs.getInt("JoinMessage") == 1;
                JoinFirework = rs.getInt("JoinFirework") == 1;
                Vanish = rs.getInt("Vanish") == 1;
                HidePlayer = rs.getInt("HidePlayer") == 1;
                SpeedBoost = rs.getInt("SpeedBoost") == 1;
                JumpBoost = rs.getInt("JumpBoost") == 1;
                System.out.println(Fly.toString());
                System.out.println(JoinMessage.toString());
                System.out.println(JoinFirework.toString());
                System.out.println(Vanish.toString());
                System.out.println(HidePlayer.toString());
                System.out.println(SpeedBoost.toString());
                System.out.println(JumpBoost.toString());
            } else {
                Fly = false;
                JoinMessage = true;
                JoinFirework = true;
                Vanish = false;
                HidePlayer = false;
                SpeedBoost = false;
                JumpBoost = false;
                PreparedStatement statement2 = DevLobby.getConnection().prepareStatement("INSERT INTO LobbyPlayerData (ID, UUID, FLY, JoinMessage, JoinFirework, Vanish, HidePlayer, SpeedBoost, JumpBoost) VALUES (" +
                        "default," +
                        "'" + uuid + "'," +
                        false + "," +
                        true + "," +
                        true + "," +
                        false + "," +
                        false + "," +
                        false + "," +
                        false +
                        ");");
                statement2.executeUpdate();
                statement2.close();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
    public UUID getUUID() {
        return uuid;
    }
    public Boolean getFly() { return Fly; }
    public Boolean getJoinMessage() { return JoinMessage; }
    public Boolean getJoinFirework() { return JoinFirework; }
    public Boolean getVanish() { return Vanish; }
    public Boolean getHidePlayer() { return HidePlayer; }
    public Boolean getSpeedBoost() { return SpeedBoost; }
    public Boolean getJumpBoost() { return JumpBoost; }

    public String getFlyString() { if (Fly) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }
    public String getJoinMessageString() { if (JoinMessage) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }
    public String getJoinFireworkString() { if (JoinFirework) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }
    public String getVanishString() { if (Vanish) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }
    public String getHidePlayerString() { if (HidePlayer) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }
    public String getSpeedBoostString() { if (SpeedBoost) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }
    public String getJumpBoostString() { if (JumpBoost) { return colorize("&aTrue"); } else { return colorize("&cFalse"); } }

}
