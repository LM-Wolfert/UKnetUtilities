package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.elgamer.UKnetUtilities.Main;
import me.elgamer.UKnetUtilities.commands.Tpll;

public class CommandPreProcess implements Listener {

	public CommandPreProcess(Main plugin) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

	}

	@EventHandler
	public void chatEvent(PlayerCommandPreprocessEvent e) {

		String message = e.getMessage();
		
		if (message.startsWith("/tpll")) {
			
			e.setCancelled(true);
			Player p = e.getPlayer();
			
			String[] m = message.split(" ");
			
			for (int i = 0; i < m.length-1; i++) {
				
				String[] args = new String[m.length-1];
				args[i] = m[i+1];
				Tpll.onCommand(p, args);
				
			}
				
			
			
			
		}


	}
}
