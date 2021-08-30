package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.elgamer.UKnetUtilities.Main;
import mineverse.Aust1n46.chat.api.MineverseChatAPI;

public class JoinEvent implements Listener {
	
	public JoinEvent(Main plugin) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Main.vc.add(MineverseChatAPI.getOnlineMineverseChatPlayer(e.getPlayer()));
		
	}

}
