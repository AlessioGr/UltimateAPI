package de.warsteiner.datax.command.sub;

import org.bukkit.command.CommandSender;

import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.command.UltimateSubCommand;
 
public class HelpSub extends UltimateSubCommand {

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "See the Plugin's Commands";
	}

	@Override
	public void perform(CommandSender sender, String[] args) {
		if (args.length == 1) {
			Messages.sendHelp(sender);
		} else {
			sender.sendMessage(Messages.prefix + "Correct Usage§8: §6/Ultimateapi help");
		}
	}

	@Override
	public int getTabLength() {
		return 1;
	}

	@Override
	public String FormatTab() {
		return "command help";
	}
 
}
