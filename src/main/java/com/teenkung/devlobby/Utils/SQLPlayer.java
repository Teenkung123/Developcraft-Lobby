package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;
import com.vomarek.hideitem.HideItemAPI;
import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
    //public UUID getUUID() { return uuid; }
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

    //private Boolean flip(Boolean input) { if (input) { return false; } else { return true; }}

    public void setFly(Boolean allow) {
        try {
            Fly = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET Fly = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
            executeFly();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeFly() {
        if (Fly) {
            getPlayer().setAllowFlight(true);
            getPlayer().setFlying(true);
        } else {
            getPlayer().setAllowFlight(false);
            getPlayer().setFlying(false);
        }
    }

    public void setJoinMessage(Boolean allow) {
        try {
            JoinMessage = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET JoinMessage = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setJoinFirework(Boolean allow) {
        try {
            JoinFirework = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET JoinFirework = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setVanish(Boolean allow) {
        try {
            Vanish = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET Vanish = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
            executeVanish();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeVanish() {
        if (Vanish) {
            if (!VanishAPI.isInvisible(getPlayer())) {
                VanishAPI.hidePlayer(getPlayer());
            }
        } else {
            if (VanishAPI.isInvisible(getPlayer())) {
                VanishAPI.showPlayer(getPlayer());
            }
        }
    }

    public void setHidePlayer(Boolean allow) {
        try {
            HidePlayer = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET HidePlayer = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
            executeHidePlayer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeHidePlayer() {
        if (HidePlayer) {
            HideItemAPI.hideFor(getPlayer());
        } else {
            HideItemAPI.showFor(getPlayer());
        }
    }

    public void setSpeedBoost(Boolean allow) {
        try {
            SpeedBoost = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET SpeedBoost = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
            executeSpeedBoost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeSpeedBoost() {
        if (SpeedBoost) {
            getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 5));
        } else {
            for (PotionEffect effect : getPlayer().getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.SPEED)) getPlayer().removePotionEffect(effect.getType());
            }
        }
    }

    public void setJumpBoost(Boolean allow) {
        try {
            JumpBoost = allow;
            PreparedStatement statement = DevLobby.getConnection().prepareStatement("UPDATE LobbyPlayerData SET JumpBoost = " + allow + " WHERE UUID = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.executeUpdate();
            statement.close();
            executeJumpBoost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeJumpBoost() {
        if (JumpBoost) {
            getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 5));
        } else {
            for (PotionEffect effect : getPlayer().getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.JUMP)) getPlayer().removePotionEffect(effect.getType());
            }
        }
    }

    public void forceExecuteFly(Boolean action) {
        if (action) {
            getPlayer().setAllowFlight(true);
            getPlayer().setFlying(true);
        } else {
            getPlayer().setAllowFlight(false);
            getPlayer().setFlying(false);
        }
    }

    public void forceExecuteVanish(Boolean action) {
        if (action) {
            VanishAPI.hidePlayer(getPlayer());
        } else {
            VanishAPI.showPlayer(getPlayer());
        }
    }

    public void forceExecuteHidePlayer(Boolean action) {
        if (action) {
            HideItemAPI.hideFor(getPlayer());
        } else {
            HideItemAPI.showFor(getPlayer());
        }
    }

    public void forceExecuteSpeedBoost(Boolean action) {
        if (action) {
            getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 5));
        } else {
            for (PotionEffect effect : getPlayer().getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.SPEED)) getPlayer().removePotionEffect(effect.getType());
            }
        }
    }

    public void forceExecuteJumpBoost(Boolean action) {
        if (action) {
            getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 5));
        } else {
            for (PotionEffect effect : getPlayer().getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.JUMP)) getPlayer().removePotionEffect(effect.getType());
            }
        }
    }

    public void executeAll() {
        executeFly();
        executeVanish();
        executeHidePlayer();
        executeSpeedBoost();
        executeJumpBoost();
    }
}
