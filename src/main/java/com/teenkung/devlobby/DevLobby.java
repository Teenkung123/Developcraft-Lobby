package com.teenkung.devlobby;

import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionEventHandler;
import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionItemBuilder;
import com.teenkung.devlobby.Handlers.*;
import com.teenkung.devlobby.Utils.ItemBuilderTemplate;
import com.teenkung.devlobby.Utils.LobbyDatabase;
import de.myzelyam.supervanish.SuperVanish;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is the main class of Developcraft Lobby Plugin
//Used to call method when server start or stop

public final class DevLobby extends JavaPlugin {

    private static DevLobby Instance;

    private static Connection connection;
    private static LobbyDatabase database;

    //This defines all Vault API Thing please don't touch
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ;
    private static Permission perms;
    private static Chat chat;


    //This Method will call when Server start (Plugin Load)
    @Override
    public void onEnable() {

        //Vault thing please don't touch
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();

        //get Instance of this class and store it in variable
        Instance = this;

        //Load configuration file
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Connect to database
        database = new LobbyDatabase();
        try {
            database.Connect();
            database.createTable();
            connection = database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PlayerOptionItemBuilder.updateItemBuilder();
        ItemBuilderTemplate.loadTemplate();

        //Register Events
        Bukkit.getPluginManager().registerEvents(new ChatHandler(), this);
        Bukkit.getPluginManager().registerEvents(new JoinHandler(), this);
        Bukkit.getPluginManager().registerEvents(new QuitHandler(), this);
        Bukkit.getPluginManager().registerEvents(new FoodHandler(), this);
        Bukkit.getPluginManager().registerEvents(new DamageHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerOptionEventHandler(), this);

    }

    //This method will call when Server Stop (Plugin Unload)
    @Override
    public void onDisable() {
        //Vault thing please don't touch
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    //This method is for getting this class's Instance from another class
    public static DevLobby getInstance() { return Instance; }
    public static Connection getConnection() { return connection; }
    public static LobbyDatabase getDatabase() { return database; }

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

    public static ArrayList<String> colorizeArray(ArrayList<String> array) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : array) {
            result.add(colorize(str));
        }
        return result;
    }


    //ALL BELOW THIS LINE IS VAULT THING PLEASE DON'T TOUCH

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            econ = null;
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) { chat = null; return; }
        chat = rsp.getProvider();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) { perms = null; return; }
        perms = rsp.getProvider();
    }

    @SuppressWarnings("NullableProblems")
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
            return false;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    //Ends of vault section

    public ArrayList<String> replaceAllinArray(ArrayList<String> input, String regex, String replacement, Boolean colorize) {
        ArrayList<String> ret = new ArrayList<>();
        for (String str : input) {
            str = str.replaceAll(regex, replacement);
            if (colorize) {str = colorize(str);}
            ret.add(str);
        }
        return ret;
    }

    public static String replaceAll(String input, String regex, String replacement, Boolean colorize) {
        if (colorize) {
            return colorize(input.replaceAll(regex, replacement));
        } else {
            return input.replaceAll(regex, replacement);
        }
    }
}
