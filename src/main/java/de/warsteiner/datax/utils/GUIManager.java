package de.warsteiner.datax.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.warsteiner.datax.UltimateAPI;


public class GUIManager {
	
	private PluginAPI up = UltimateAPI.getInstance().getAPI(); 

	public void openInventory(Player player, int size, String name) {
		final Inventory inv = Bukkit.createInventory(null, size * 9, up.toHex(name).replaceAll("&", "§"));
		player.openInventory(inv);
	}
	
}
