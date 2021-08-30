package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.elgamer.UKnetUtilities.Main;
import mineverse.Aust1n46.chat.api.MineverseChatAPI;

public class QuitEvent implements Listener {
	
	public QuitEvent(Main plugin) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		Main.vc.remove(MineverseChatAPI.getOnlineMineverseChatPlayer(e.getPlayer()));
		
	}

}
