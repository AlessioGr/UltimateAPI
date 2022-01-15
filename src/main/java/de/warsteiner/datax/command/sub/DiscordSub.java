package de.warsteiner.datax.command.sub;

import org.bukkit.command.CommandSender;

import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.command.UltimateSubCommand;
 
public class DiscordSub extends UltimateSubCommand {

	@Override
	public String getName() {
		return "discord";
	}

	@Override
	public String getDescription() {
		return "Join the Developer's Discord";
	}

	@Override
	public void perform(CommandSender sender, String[] args) {
		if (args.length == 1) {
			sender.sendMessage(Messages.prefix + "§7Join the Discord here -> §bhttps://discord.com/invite/sy9nDEpYVp");
		} else {
			sender.sendMessage(Messages.prefix + "Correct Usage§8: §6/Ultimateapi discord");
		}
	}

	@Override
	public int getTabLength() {
		return 1;
	}

	@Override
	public String FormatTab() {
		return "command discord";
	}

}

