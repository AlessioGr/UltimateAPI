package de.warsteiner.datax.utils;

import org.bukkit.command.CommandSender;

public class Messages {
	
	public static String prefix = "§9UltimateAPI §8-> §7";
	public static String noperm = "§cYou dont have permissions!";
	public static String not_found = "§7Please use §9/Ultimateapi help"; 
	
	public static void sendHelp(CommandSender sender) {
		sender.sendMessage("§7");
		sender.sendMessage(" §8| §9UltimateAPI §8- §4Admin Help §8|"); 
		sender.sendMessage("§8-> §6/Ultimateapi help"); 
		sender.sendMessage("§8-> §6/Ultimateapi version"); 
		sender.sendMessage("§8-> §6/Ultimateapi discord");
		sender.sendMessage("§8-> §6/Ultimateapi check");
		sender.sendMessage("§7");
	}
	
}
