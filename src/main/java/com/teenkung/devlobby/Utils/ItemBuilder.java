package com.teenkung.devlobby.Utils;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;
    private NBTItem itemNBT;

    /**
     * The constructor method.
     * @param material the material of the item you are building.
     * @param amount   the amount of items in the item you are building.
     */
    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemNBT = new NBTItem(this.itemStack);
    }

    public ItemBuilder(ItemStack stack) {
        this.itemStack = stack;
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemNBT = new NBTItem(this.itemStack);
    }

    public String getDisplayName() {
        return itemMeta.getDisplayName();
    }

    public String getStringNBT(String key) {
        return this.itemNBT.getString(key);
    }

    private void updateItemMeta() {
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemNBT = new NBTItem(this.itemStack);
    }

    /**
     * Sets the display name of the item, this name can be viewed by hovering over the * item in your inventory or holding it in your hand.
     * @param name the name to set for the item.
     * @return the ItemBuilder.
     */
    public ItemBuilder setDisplayName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * @param lines the strings to set as the item lore.
     * @return the ItemBuilder.
     */
    public ItemBuilder setLore(String... lines) {
        this.itemMeta.setLore(Arrays.asList(lines));
        return this;
    }

    public ItemBuilder setLoreByArray(ArrayList<String> lines) {
        this.itemMeta.setLore(lines);
        return this;
    }

    public ItemBuilder setStringNBT(String path, String value) {
        updateItemMeta();
        this.itemNBT.setString(path, value);
        this.itemNBT.applyNBT(this.itemStack);
        this.itemMeta = this.itemStack.getItemMeta();
        return this;
    }

    public ItemBuilder setGlowing(Boolean glow) {
        if (glow) {
            this.itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            this.itemMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
            this.itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    /**
     * @return the ItemStack that has been created.
     */
    public ItemStack build() {
        this.updateItemMeta();
        return this.itemStack;
    }
}
