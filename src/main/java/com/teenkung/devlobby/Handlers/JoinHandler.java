package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.DevLobby;
import com.teenkung.devlobby.Utils.*;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import static com.teenkung.devlobby.DevLobby.colorize;

public class JoinHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SQLPlayer SQLplayer = SQLManager.addPlayer(player);
        Rank Group = Rank.getRank(DevLobby.getPermissions().getPrimaryGroup(player));

        System.out.println(colorize("&a" + player.getName() + "'s UUID is " + player.getUniqueId() + "&e and IP " + player.getAddress()));

        player.setHealth(20);
        player.setFoodLevel(20);
        player.teleport(ConfigLoader.getSpawnLocation());
        if (SQLplayer.getJoinFirework() && Group != Rank.DEFAULT) {
            SummonFirework(player, Group);
        }
        if (SQLplayer.getJoinMessage() && Group != Rank.DEFAULT) {
            event.setJoinMessage(DevLobby.replaceAll(DevLobby.replaceAll(ConfigLoader.getPlayerJoinMessage(Group), "<rank>", DevLobby.getChat().getGroupPrefix(player.getWorld(), Rank.getString(Group)), false), "<name>", player.getName(), true));
        } else {
            event.setJoinMessage(null);
        }
        //PlayerOptionGUI.openGUI(player);
        player.getInventory().clear();
        PlayerManager manager = GadgetsMenuAPI.getPlayerManager(player);
        setSlot(player);
        manager.giveMenuSelector();

        SQLplayer.executeAll();
    }

    public static void setSlot(Player player) {
        player.getInventory().setItem(0, ItemBuilderTemplate.getLobbySelector());
        player.getInventory().setItem(1, ItemBuilderTemplate.getRankShopItem());
        player.getInventory().setItem(8, ItemBuilderTemplate.getPlayerOption());
        player.getInventory().setItem(7, ItemBuilderTemplate.getPVPItem());
    }


    private void SummonFirework(Player player, Rank Group) {
        for (int i = 0; i < 5; i++) {

            Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();

            if (Group == Rank.GUARDIAN || Group == Rank.GUARDIANF) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).build());
            } else if (Group == Rank.HERO) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.PURPLE).withFade(Color.FUCHSIA).with(FireworkEffect.Type.BALL_LARGE).build());
            } else if (Group == Rank.TITAN) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.AQUA).withFade(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build());
            } else if (Group == Rank.DRAGON) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.GREEN).withFade(Color.OLIVE).withFlicker().with(FireworkEffect.Type.BALL_LARGE).build());
            } else if (Group == Rank.SUPREME) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build());
            } else if (Group == Rank.YOUTUBER) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.RED).withFade(Color.RED).withFade(Color.WHITE).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
            } else if (Group == Rank.ADMIN) {
                meta.addEffect(FireworkEffect.builder().withColor(Color.BLACK).withColor(Color.WHITE).withFade(Color.RED).withColor(Color.WHITE).withFlicker().with(FireworkEffect.Type.BALL_LARGE).build());
            } else {
                meta.addEffect(FireworkEffect.builder().withColor(Color.BLACK).withColor(Color.WHITE).build());
            }
            meta.setPower(1);
            firework.setFireworkMeta(meta);
        }
    }
}
