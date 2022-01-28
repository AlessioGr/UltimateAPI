package de.warsteiner.datax.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.warsteiner.datax.UltimateAPI;
import de.warsteiner.datax.utils.SQLManager;
 

public class PlayerExistEvent implements Listener {

	private static UltimateAPI plugin = UltimateAPI.getPlugin();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		plugin.getExecutor().execute(() -> {

			Player player = event.getPlayer();
			SQLManager l = plugin.getSQLManager();
	 
			UUID UUID = player.getUniqueId();
			String name = player.getName();

			if (l.ExistPlayer("" + UUID) == false) {
				l.createPlayerInList(""+UUID, name);
			} else {
				l.updateName(""+UUID, name);
				l.updateUUID(name, ""+UUID);
			}

		});

	}
	
}
