package de.warsteiner.datax.command.sub;
 
import org.bukkit.command.CommandSender;

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.database.DatabaseInit;
import de.warsteiner.datax.utils.Messages;
import de.warsteiner.datax.utils.command.UltimateSubCommand;

public class CheckSub extends UltimateSubCommand {

	private static UltimateAPI plugin = UltimateAPI.getInstance();

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
