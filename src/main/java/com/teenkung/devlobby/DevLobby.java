package com.teenkung.devlobby;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.teenkung.devlobby.Command.PVPCommand;
import com.teenkung.devlobby.Command.WarpGUI;
import com.teenkung.devlobby.GUIs.BuyRank.BuyRankBuilder;
import com.teenkung.devlobby.GUIs.BuyRank.BuyRankHandler;
import com.teenkung.devlobby.GUIs.LobbySelector.LobbySelectorGUI;
import com.teenkung.devlobby.GUIs.LobbySelector.LobbySelectorGUIHandler;
import com.teenkung.devlobby.GUIs.LobbySelector.LobbySelectorLoader;
import com.teenkung.devlobby.GUIs.LobbySelector.PluginMessage;
import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionEventHandler;
import com.teenkung.devlobby.GUIs.PlayerOption.PlayerOptionItemBuilder;
import com.teenkung.devlobby.Handlers.*;
import com.teenkung.devlobby.Utils.*;
import com.teenkung.devlobby.pvp.PVPHandler;
import com.teenkung.devlobby.pvp.PVPLoader;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is the main class of Developcraft Lobby Plugin
//Used to call method when server start or stop

public final class DevLobby extends JavaPlugin {

    private static DevLobby Instance;

    private static Connection connection;

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
        LobbyDatabase database = new LobbyDatabase();
        try {
            database.Connect();
            database.createTable();
            connection = database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JumpPadHandler.setPressurePlateBlock();
        PluginMessage.registerPluginMessageListener();
        PlayerOptionItemBuilder.updateItemBuilder();
        ItemBuilderTemplate.loadTemplate();
        LobbySelectorGUI.createLobbySelectorGUI();
        LobbySelectorLoader.setupLobbyItem();
        LobbySelectorLoader.updateLobbyItem();
        BuyRankBuilder.loadRankBuilder();
        PVPLoader.loadConfig();

        //Register Events
        Bukkit.getPluginManager().registerEvents(new ChatHandler(), this);
        Bukkit.getPluginManager().registerEvents(new JoinHandler(), this);
        Bukkit.getPluginManager().registerEvents(new QuitHandler(), this);
        Bukkit.getPluginManager().registerEvents(new FoodHandler(), this);
        Bukkit.getPluginManager().registerEvents(new DamageHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerOptionEventHandler(), this);
        Bukkit.getPluginManager().registerEvents(new LobbySelectorGUIHandler(), this);
        Bukkit.getPluginManager().registerEvents(new JumpPadHandler(), this);
        Bukkit.getPluginManager().registerEvents(new BuyRankHandler(), this);
        Bukkit.getPluginManager().registerEvents(new InteractionHandler(), this);
        Bukkit.getPluginManager().registerEvents(new ColoredSignHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PVPHandler(), this);
        Bukkit.getPluginManager().registerEvents(new DeathHandler(), this);

        Objects.requireNonNull(getCommand("open-warp-ui")).setExecutor(new WarpGUI());
        Objects.requireNonNull(getCommand("leave-pvp")).setExecutor(new PVPCommand());
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (ItemStack stack : player.getInventory().getContents()) {
                if (stack == null) { continue; }
                if (stack.getType().equals(Material.DIAMOND_HELMET) || stack.getType().equals(Material.DIAMOND_CHESTPLATE) || stack.getType().equals(Material.DIAMOND_LEGGINGS) || stack.getType().equals(Material.DIAMOND_BOOTS) || stack.getType().equals(Material.NETHERITE_SWORD)) {
                    player.sendMessage(colorize("&eThe Developer has reload the plugin. &cSystem will force all Player to quit PVP Mode"));
                    player.getInventory().clear();
                    Bukkit.getScheduler().runTaskLater(this, () -> {
                        PlayerManager manager = GadgetsMenuAPI.getPlayerManager(player);
                        manager.giveMenuSelector();
                        SQLPlayer sql = SQLManager.getPlayer(player);
                        sql.executeAll();
                        JoinHandler.setSlot(player);
                    }, 2);
                    break;
                }
            }
        }

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getWorld().equals(ConfigLoader.getSpawnLocation().getWorld()) && !player.isOp()) {
                    player.teleport(ConfigLoader.getSpawnLocation());
                }
            }
        }, 20, 20);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, LobbySelectorLoader::updateLobbyItem, 10, 200);

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


    public static String colorize(String message) {
        return IridiumColorAPI.process(message);
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
