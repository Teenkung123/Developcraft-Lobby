package com.teenkung.devlobby.Handlers;

import com.teenkung.devlobby.Utils.ConfigLoader;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class JumpPadHandler implements Listener {

    private static Material PlateBlock;
    private static Integer Power;

    public static void setPressurePlateBlock() {
        PlateBlock = ConfigLoader.getMaterial(ConfigLoader.getConfig().getString("JumpPad.Block", "STONE_PRESSURE_PLATE"));
        Power = ConfigLoader.getConfig().getInt("JumpPad.Power");
    }

    @EventHandler
    public void onPress(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            Block type = event.getClickedBlock();
            if (type != null) {
                if (type.getType().equals(PlateBlock)) {
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(Power));
                }
            }

        }
    }

}
