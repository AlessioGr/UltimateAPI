package de.warsteiner.datax.command.sub;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.database.DatabaseInit;
import de.warsteiner.datax.module.UltimateModule;
import de.warsteiner.datax.utils.GUIManager;
import de.warsteiner.datax.utils.ItemAPI;
import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.UpdateChecker;
import de.warsteiner.datax.utils.command.UltimateSubCommand;

public class PluginsSub extends UltimateSubCommand {

	private static UltimateAPI plugin = UltimateAPI.getInstance();
	private static ItemAPI im = UltimateAPI.getInstance().getItemAPI();
	private static GUIManager gm = UltimateAPI.getInstance().getGUIManager();

	@Override
	public String getName() {
		return "plugins";
	}

	@Override
	public String getDescription() {
		return "See the Plugin's Hooks";
	}

	@Override
	public void perform(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if (sender instanceof Player) {

				Player player = (Player) sender;
				if (plugin.getModuleRegistry().getModuleList().size() != 0) {
				gm.openInventory(player, 3, "§bPlugins");
				 
					plugin.getExecutor().execute(() -> {
						InventoryView inv = player.getOpenInventory();

						List<UltimateModule> list = plugin.getModuleRegistry().getModuleList();

						int size = list.size();

						if (inv.getTitle().equalsIgnoreCase("§bPlugins")) {

							for (int i = 0; i < size; i++) {

								if (size >= i) {
									UltimateModule m = list.get(i);

									ItemStack it = im.createItemStack(player.getName(), m.getIcon());
									ItemMeta meta = it.getItemMeta();

									meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

									ArrayList<String> l = new ArrayList<String>();

									if (m.getDescription() != null) {
										for (String line : m.getDescription()) {
											l.add(line);
										}
									} else {
										l.add("§8- §cNo Description §8-");
									}

									int addons = plugin.getModuleRegistry().getAddonsOf(m.getPluginName()).size();

									l.add("§7");
									l.add("§7Name §8: §a" + m.getName());
									l.add("§7Addons §8: §a" + addons);
									l.add("§7Version §8: §b" + m.getVersion());
									l.add("§7By §8: §c" + m.getDeveloper());

									meta.setLore(l);

									meta.setDisplayName("§8< " + m.getName() + " §8>");

									it.setItemMeta(meta);

									inv.setItem(i, it);
								}
							}

							ItemStack it = im.createItemStack(player.getName(), "BARRIER");
							ItemMeta meta = it.getItemMeta();

							meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

							meta.setDisplayName("§8< §cClose §8>");

							it.setItemMeta(meta);

							inv.setItem(26, it);

						}

					});
					return;
				} else {
					sender.sendMessage(Messages.prefix + "§cThere are no Ultimate-Plugins installed.");
					return;
				}

			} else {
				sender.sendMessage(Messages.prefix + "§cThis Command is only for players!");
				return;
			}

		} else {
			sender.sendMessage(Messages.prefix + "Correct Usage§8: §6/Ultimateapi plugins");
			return;
		}
	}

	@Override
	public int getTabLength() {
		return 1;
	}

	@Override
	public String FormatTab() {
		return "command plugins";
	}

}
