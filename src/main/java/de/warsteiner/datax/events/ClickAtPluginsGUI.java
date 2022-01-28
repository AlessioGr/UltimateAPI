package de.warsteiner.datax.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.module.UltimateModule;
import de.warsteiner.datax.module.UltimateSubModule;
import de.warsteiner.datax.utils.GUIManager;
import de.warsteiner.datax.utils.ItemAPI;

public class ClickAtPluginsGUI implements Listener {

	private static UltimateAPI plugin = UltimateAPI.getInstance();
	private static ItemAPI im = UltimateAPI.getInstance().getItemAPI();
	private static GUIManager gm = UltimateAPI.getInstance().getGUIManager();

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}

		if (e.getView().getTitle() == null) {
			return;
		}

		if (e.getCurrentItem().getItemMeta() == null) {
			return;
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
			return;
		}

		Player player = (Player) e.getWhoClicked();
		String dis = e.getCurrentItem().getItemMeta().getDisplayName();
		String title =  e.getView().getTitle();
		
		if (title.equalsIgnoreCase("§bPlugins")) {

			if (dis.equalsIgnoreCase("§8< §cClose §8>")) {
				player.closeInventory();
			}

			for (UltimateModule m : plugin.getModuleRegistry().getModuleList()) {
				String l = "§8< " + m.getName() + " §8>";
				if (dis.equalsIgnoreCase(l)) {
					createSubMenu(player, m);
				}
			}

			e.setCancelled(true);
		}
		
		if(title.contains("§bPlugin")) {
		 
			for (UltimateModule m : plugin.getModuleRegistry().getModuleList()) {
				String l = "§bPlugin §8: "+m.getName();
				if (title.equalsIgnoreCase(l)) {
					if (dis.equalsIgnoreCase("§8< §cClose §8>")) {
						player.closeInventory();
					} else if (dis.equalsIgnoreCase("§8< §cGo Back §8>")) {
						player.performCommand("ultimateapi plugins");
					} else if (dis.equalsIgnoreCase("§8< §aReload Plugin's Configs §8>")) {
						player.closeInventory(); 
						m.reloadConfig(player);
					} else if (dis.equalsIgnoreCase("§8< §aReload Addon's Configs §8>")) {
						player.closeInventory(); 
						for(UltimateSubModule subm : plugin.getModuleRegistry().getAddonsOf(m.getPluginName())) {
							subm.reloadConfig(player);
						}
					}
					 
				}
			}
			
			e.setCancelled(true);
			
		}

	}
	
	public void createSubMenu(Player player, UltimateModule m) {
		String title = "§bPlugin §8: "+m.getName();
		gm.openInventory(player, 3, title);
		 
		plugin.getExecutor().execute(() -> {  
			InventoryView inv = player.getOpenInventory();
			if(inv.getTitle().equalsIgnoreCase(title)) { 
				ItemStack it = im.createItemStack(player.getName(), "RED_DYE");
				ItemMeta meta = it.getItemMeta();
				 
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				 
				meta.setDisplayName("§8< §cGo Back §8>");
				 
				it.setItemMeta(meta);
				
				inv.setItem(18, it); 
			}
			if(inv.getTitle().equalsIgnoreCase(title)) { 
				ItemStack it = im.createItemStack(player.getName(), "BARRIER");
				ItemMeta meta = it.getItemMeta();
				 
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				 
				meta.setDisplayName("§8< §cClose §8>");
				 
				it.setItemMeta(meta);
				
				inv.setItem(26, it); 
			}
			if(inv.getTitle().equalsIgnoreCase(title)) { 
				ItemStack it = im.createItemStack(player.getName(), "SLIME_BALL");
				ItemMeta meta = it.getItemMeta();
				 
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				 
				meta.setDisplayName("§8< §aReload Plugin's Configs §8>");
				 
				it.setItemMeta(meta);
				
				inv.setItem(12, it); 
			}
			if(inv.getTitle().equalsIgnoreCase(title)) { 
				if(plugin.getModuleRegistry().getAddonsOf(m.getPluginName()).size() != 0) {
					ItemStack it = im.createItemStack(player.getName(), "LIME_DYE");
					ItemMeta meta = it.getItemMeta();
					 
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					 
					meta.setDisplayName("§8< §aReload Addon's Configs §8>");
					 
					it.setItemMeta(meta);
					
					inv.setItem(14, it); 
				} else {
					ItemStack it = im.createItemStack(player.getName(), "CLAY_BALL");
					ItemMeta meta = it.getItemMeta();
					 
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					 
					meta.setDisplayName("§8< §cNo Addons Installed §8>");
					 
					it.setItemMeta(meta);
					
					inv.setItem(14, it); 
				}
			}
		});
	}

}
