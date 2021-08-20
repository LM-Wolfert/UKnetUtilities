package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.elgamer.UKnetUtilities.Main;

public class CommandListener implements Listener {

	public CommandListener(Main plugin) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

	}

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {

		String[] message = e.getMessage().split(" ");
		
		if (message[0].length() == 5) {
			
			switch(message[0]) {

				case "/rpll":
					e.getMessage().replace("/rpll", "/tpll");
					break;
				case "/fpll":
					e.getMessage().replace("/fpll", "/tpll");
					break;
				case "/ypll":
					e.getMessage().replace("/ypll", "/tpll");
					break;
				case "/gpll":
					e.getMessage().replace("/gpll", "/tpll");
					break;
				case "/tokk":
					e.getMessage().replace("/tokk", "/tpll");
					break;
				case "/t[;;":
					e.getMessage().replace("/t[;;", "/tpll");
					break;
				case "/y[;;":
					e.getMessage().replace("/y[;;", "/tpll");
					break;
				case "/rokk":
					e.getMessage().replace("/rokk", "/tpll");
					break;
				case "/r[;;":
					e.getMessage().replace("/r[;;", "/tpll");
					break;
			}
			
		}
		
	}
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent c) {
		
		if (c.getMessage().startsWith("tpll")) {
			c.getPlayer().performCommand("/" + c.getMessage());
		}
		
	}
}
