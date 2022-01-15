package de.warsteiner.datax.utils.command;

import java.util.ArrayList;
import java.util.List;

public class  UltimateSubCommandRegistry  {
	  
    private final List<UltimateSubCommand> subCommandList = new ArrayList<UltimateSubCommand>();
 
    public List<UltimateSubCommand> getSubCommandList() {
        return subCommandList;
    }

}
