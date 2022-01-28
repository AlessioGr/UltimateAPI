package de.warsteiner.datax.module;

import java.util.ArrayList;
import java.util.List;
 

public class ModuleRegistry {
	
	private final List<UltimateModule> mlist = new ArrayList<UltimateModule>();
	private final List<UltimateSubModule> alist = new ArrayList<UltimateSubModule>();

	public List<UltimateSubModule> getAddonList() {
		return alist;
	}
	
	public List<UltimateModule> getModuleList() {
		return mlist;
	}

	public ArrayList<UltimateSubModule> getAddonsOf(String name) {
		ArrayList<UltimateSubModule> l = new ArrayList<UltimateSubModule>();
		for(UltimateSubModule line : getAddonList()) {
			if(line.getAddonOf().equalsIgnoreCase(name)) {
				l.add(line);
			}
		}
		return l;
	}
	
}
