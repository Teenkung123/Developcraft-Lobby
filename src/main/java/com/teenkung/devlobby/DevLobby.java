package com.teenkung.devlobby;

import com.teenkung.devlobby.Utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is the main class of Developcraft Lobby Plugin
//Used to call method when server start or stop

public final class DevLobby extends JavaPlugin {

    private static DevLobby Instance;

    //This Method will call when Server start (Plugin Load)
    @Override
    public void onEnable() {

        //get Instance of this class and store it in variable
        Instance = this;

        ConfigLoader.LoadConfig();

    }

    //This method will call when Server Stop (Plugin Unload)
    @Override
    public void onDisable() {

    }

    //This method is for getting this class's Instance from another class
    public static DevLobby getInstance() { return Instance; }

    public static String colorize(String s) {
        if (s == null || s.equals(""))
            return "";
        if (!Bukkit.getVersion().contains("1.16") && !Bukkit.getVersion().contains("1.17"))
            return ChatColor.translateAlternateColorCodes('&', s);
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher match = pattern.matcher(s);
        while (match.find()) {
            String hexColor = s.substring(match.start(), match.end());
            s = s.replace(hexColor, ChatColor.valueOf(hexColor).toString());
            match = pattern.matcher(s);
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
