package com.teenkung.devlobby.Utils;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;
    private NBTItem itemNBT;
    private Integer amount;

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemNBT = new NBTItem(this.itemStack);
        this.amount = amount;
    }

    public ItemBuilder(ItemStack stack) {
        this.itemStack = stack;
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemNBT = new NBTItem(this.itemStack);
    }

    public String getStringNBT(String key) {
        return this.itemNBT.getString(key);
    }

    private void updateItemMeta() {
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemStack.setAmount(this.amount);
        this.itemNBT = new NBTItem(this.itemStack);
    }

    public ItemBuilder setDisplayName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        if (amount > 64) {
            this.amount = 64;
        } else if (amount <= 0) {
            this.amount = 1;
        } else {
            this.amount = amount;
        }
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

    public ItemStack build() {
        this.updateItemMeta();
        return this.itemStack;
    }
}
