package com.teenkung.devlobby.pvp;

import com.teenkung.devlobby.Utils.ConfigLoader;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PVPLoader {

    private static String message;
    private static String message2;
    private static String message3;
    private static String message4;

    private static ItemStack Helmet;
    private static ItemStack ChestPlate;
    private static ItemStack Leggings;
    private static ItemStack Boots;

    private static ItemStack Sword;
    private static ItemStack Axe;
    private static ItemStack Bow;

    public static void loadConfig() {
        message = ConfigLoader.getConfig().getString("PVP.Message");
        message2 = ConfigLoader.getConfig().getString("PVP.Message-2");
        message3 = ConfigLoader.getConfig().getString("PVP.Message-3");
        message4 = ConfigLoader.getConfig().getString("PVP.Message-4");

        Helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        Helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        ChestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ChestPlate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        Leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        Leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        Boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        Boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        Sword = new ItemStack(Material.NETHERITE_SWORD, 1);
        Sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);

        Axe = new ItemStack(Material.NETHERITE_AXE, 1);
        Axe.addEnchantment(Enchantment.DAMAGE_ALL, 3);

        Bow = new ItemStack(Material.BOW, 1);
        Bow.addEnchantment(Enchantment.ARROW_DAMAGE, 4);

    }

    public static String getMessage() { return message; }
    public static String getMessage2() { return message2; }
    public static String getMessage3() { return message3; }
    public static String getMessage4() { return message4; }

    public static ItemStack getHelmet() { return Helmet; }
    public static ItemStack getChestPlate() { return ChestPlate; }
    public static ItemStack getLeggings() { return Leggings; }
    public static ItemStack getBoots() { return Boots; }

    public static ItemStack getSword() { return Sword; }
    public static ItemStack getBow() { return Bow; }
    public static ItemStack getAxe() { return Axe; }

}
