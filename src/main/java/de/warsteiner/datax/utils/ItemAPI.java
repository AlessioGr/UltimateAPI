package de.warsteiner.datax.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemAPI {
	
	public ItemStack createItemStack(String p, String item) {
		ItemStack i;
		if (Material.getMaterial(item.toUpperCase()) == null) {
			i = generateSkull(item.replaceAll("<skull>", p));
		} else {
			i = new ItemStack(Material.valueOf(item.toUpperCase()), 1);
		}
		return i;
	}

	public ItemStack createItemStack(Player p, String item) {
		ItemStack i;
		if (Material.getMaterial(item.toUpperCase()) == null) {
			i = generateSkull(item.replaceAll("<skull>", p.getName()));
		} else {
			i = new ItemStack(Material.valueOf(item.toUpperCase()), 1);
		}
		return i;
	}

	@SuppressWarnings("deprecation")
	public ItemStack generateSkull(String owner) {
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		skullMeta.setOwner(owner);
		itemStack.setItemMeta((ItemMeta) skullMeta);
		return itemStack;
	}

}
