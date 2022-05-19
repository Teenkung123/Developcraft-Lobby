package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;

import java.sql.*;

public class LobbyDatabase {

    private Connection connection;
    private Long Time;

    public void Connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + ConfigLoader.getDatabaseHost() + ":" + ConfigLoader.getDatabasePort() + "/" + ConfigLoader.getDatabaseName() + "?useSSL=false&autoReconnect=true",
                ConfigLoader.getDatabaseUser(),
                ConfigLoader.getDatabasePassword()
        );
    }

    public Connection getConnection() { return connection; }

    public void Disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS LobbyPlayerData (" +
                    "ID int NOT NULL AUTO_INCREMENT, " +
                    "UUID VARCHAR(40)," +
                    "FLY BOOLEAN, " +
                    "JoinMessage BOOLEAN," +
                    "JoinFirework BOOLEAN," +
                    "Vanish BOOLEAN," +
                    "HidePlayer BOOLEAN," +
                    "SpeedBoost BOOLEAN," +
                    "JumpBoost BOOLEAN," +
                    "PRIMARY KEY (ID)" +
                    ");");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
