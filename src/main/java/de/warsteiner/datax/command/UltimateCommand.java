package de.warsteiner.datax.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender; 

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.command.UltimateSubCommand; 
 
public class UltimateCommand  implements CommandExecutor {
	 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	 
		int length = args.length;

		if (sender.hasPermission("ultimateapi.command")) {

			if (length == 0) {
				Messages.sendHelp(sender);
			} else {
				String ar = args[0].toLowerCase();

				if (find(ar) == null) {
					sender.sendMessage(Messages.prefix+Messages.not_found);
					return true;
				} else {

					UltimateSubCommand cmd = find(ar);

					cmd.perform(sender, args);

				}

			}

		} else {
			sender.sendMessage(Messages.prefix+Messages.noperm);
		}
		return false;
	}
	
	public UltimateSubCommand find(String given) {
		for (UltimateSubCommand subCommand : UltimateAPI.getInstance().getSubCommandRegistry().getSubCommandList()) {
			if (given.equalsIgnoreCase(subCommand.getName().toLowerCase())) {
				return subCommand;
			}
		}
		return null;
	}


}
