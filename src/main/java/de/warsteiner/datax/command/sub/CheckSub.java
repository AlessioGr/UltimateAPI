package de.warsteiner.datax.command.sub;
 
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.database.DatabaseInit;
import de.warsteiner.datax.utils.GUIManager;
import de.warsteiner.datax.utils.ItemAPI;
import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.command.UltimateSubCommand;

public class CheckSub extends UltimateSubCommand {

	private static UltimateAPI plugin = UltimateAPI.getInstance();
	private static ItemAPI im = UltimateAPI.getInstance().getItemAPI();
	private static GUIManager gm = UltimateAPI.getInstance().getGUIManager();

	@Override
	public String getName() {
		return "check";
	}

	@Override
	public String getDescription() {
		return "See the Plugin's Connection";
	}

	@Override
	public void perform(CommandSender sender, String[] args) {
		if (args.length == 1) {
			DatabaseInit databaseInit = plugin.getInit();
			
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				  
				gm.openInventory(player, 3, "§aConnection Status");
				 
				plugin.getExecutor().execute(() -> { 
					
					String i = null;
					Material m = null;
					ArrayList<String> lore = new ArrayList<String>();;
					
					
					
					if (databaseInit.getDataSource() == null) {						
						i = "§4Disconnected";
						m = Material.BARRIER; 
						lore.add("§8-> §7Connected to Database §8: §fNULL");
					} else if (databaseInit.isClosed()) {
						i = "§cDisconnected";
						m = Material.RED_DYE; 
						lore.add("§8-> §7Connected to Database §8: §fNONE"); 
					} else if (!databaseInit.isClosed() || databaseInit.getDataSource() != null) {
						i = "§aConnected";
						m = Material.GREEN_DYE; 
						lore.add("§8-> §7Connected to Database §8: §b"+databaseInit.getAuthentication().database()); 
					}
					 
					InventoryView inv = player.getOpenInventory();
					
					
					
					if(inv.getTitle().equalsIgnoreCase("§aConnection Status")) {
						
						for (int counter = 0; counter <= 26; counter++) {
							ItemStack it = im.createItemStack(player.getName(), "GRAY_STAINED_GLASS_PANE");
							ItemMeta meta = it.getItemMeta();
							
							meta.setDisplayName("§b");
							 
							it.setItemMeta(meta);
							
							inv.setItem(counter, it);
					     }
						
						ItemStack it = im.createItemStack(player.getName(), ""+m);
						ItemMeta meta = it.getItemMeta();
						
						meta.setDisplayName(i);
						
						meta.setLore(lore);
						
						it.setItemMeta(meta);
						
						inv.setItem(13, it);
						
					}
					 
				});
				 
				
			} else {
				plugin.getExecutor().execute(() -> { 
					if (databaseInit.getDataSource() == null) {
						sender.sendMessage(Messages.prefix + "§7Status : §cDISCONNECTED §8| §7Connected Database : §fNULL");
					} else if (databaseInit.isClosed()) {
						sender.sendMessage(Messages.prefix + "§7Status : §cDISCONNECTED §8| §7Connected Database : §fNONE");
					} else if (!databaseInit.isClosed() || databaseInit.getDataSource() != null) {
						sender.sendMessage(Messages.prefix + "§7Status : §aCONNECTED §8| §7Connected Database : §b"
								+ databaseInit.getAuthentication().database());
					}
				});
			}
			
			 
			
		} else {
			sender.sendMessage(Messages.prefix + "Correct Usage§8: §6/Ultimateapi check");
		}
	}

	@Override
	public int getTabLength() {
		return 1;
	}

	@Override
	public String FormatTab() {
		return "command check";
	}

}
