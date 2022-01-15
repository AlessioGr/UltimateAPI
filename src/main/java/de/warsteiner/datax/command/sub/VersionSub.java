package de.warsteiner.datax.command.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.command.UltimateSubCommand;
 
public class VersionSub extends UltimateSubCommand {

	private static UltimateAPI plugin = UltimateAPI.getInstance();

	@Override
	public String getName() {
		return "version";
	}

	@Override
	public String getDescription() {
		return "See the Plugin's Version";
	}

	@Override
	public void perform(CommandSender sender, String[] args) {
		if (args.length == 1) {
			PluginDescriptionFile d = plugin.getDescription();
			sender.sendMessage(Messages.prefix + "§7Version §8: §bv" + d.getVersion()
					+ " §7running with API Version §8: §a" + d.getAPIVersion() + "§7.");
		} else {
			sender.sendMessage(Messages.prefix + "Correct Usage§8: §6/Ultimateapi version");
		}
	}

	@Override
	public int getTabLength() {
		return 1;
	}

	@Override
	public String FormatTab() {
		return "command version";
	}

}
