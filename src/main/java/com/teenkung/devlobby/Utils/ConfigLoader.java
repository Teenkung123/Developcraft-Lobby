package com.teenkung.devlobby.Utils;

import com.teenkung.devlobby.DevLobby;

public class ConfigLoader {

    public static String getDatabaseHost() { return DevLobby.getInstance().getConfig().getString("Database.Host"); }
    public static String getDatabasePort() { return DevLobby.getInstance().getConfig().getString("Database.Port"); }
    public static String getDatabaseUser() { return DevLobby.getInstance().getConfig().getString("Database.Username"); }
    public static String getDatabasePassword() { return DevLobby.getInstance().getConfig().getString("Database.Password"); }
    public static String getDatabaseName() { return DevLobby.getInstance().getConfig().getString("Database.Database"); }

}
