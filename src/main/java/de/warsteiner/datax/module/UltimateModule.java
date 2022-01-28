package de.warsteiner.datax.module;

import java.util.ArrayList;

import org.bukkit.command.CommandSender; 

public abstract class UltimateModule {

	public abstract String getPluginName();

	public abstract String getName();
	
	public abstract ArrayList<String> getDescription();
 
	public abstract String getDeveloper();

	public abstract String getVersion();
  
	public abstract void reloadConfig(CommandSender sender);
	 
	
	public abstract String getIcon();
	
}
