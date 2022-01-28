package de.warsteiner.datax.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

import net.md_5.bungee.api.ChatColor;

public class PluginAPI {
	
	private static final Pattern pattern = Pattern.compile("(?<!\\\\)(#[a-fA-F0-9]{6})");
	
	public String toHex(String motd) {
		Matcher matcher = pattern.matcher(motd);
		while (matcher.find()) {
			String color = motd.substring(matcher.start(), matcher.end());
			motd = motd.replace(color, "" + ChatColor.of(color));
		}

		return motd;
	}
	
	public boolean isFullyGrownOld(Block block) {

		if (block.getBlockData() == null) {
			return false;
		}

		if (block.hasMetadata("placed-by-player")) {
			return false;
		}

		if (block.getType() == Material.MELON || block.getType() == Material.PUMPKIN
				|| block.getType() == Material.SUGAR_CANE) {
			return true;
		}

		BlockData bdata = block.getBlockData();
		if (bdata instanceof Ageable) {
			Ageable age = (Ageable) bdata;
			if (age.getAge() == age.getMaximumAge()) {
				return true;
			}
		}
		return false;
	}
 
}
