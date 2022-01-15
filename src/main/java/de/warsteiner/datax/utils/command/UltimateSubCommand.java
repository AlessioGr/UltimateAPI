package de.warsteiner.datax.utils.command;

import org.bukkit.command.CommandSender;
 
public abstract class UltimateSubCommand {
	
    public abstract String getName();

    public abstract String getDescription();
 
    public abstract void perform(CommandSender sender, String[] args);
	
    public abstract String FormatTab();

    public abstract int getTabLength();
     
}
