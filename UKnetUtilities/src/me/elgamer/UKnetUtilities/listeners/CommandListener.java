package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.elgamer.UKnetUtilities.Main;

public class CommandListener implements Listener {
	
	public CommandListener(Main plugin) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {

		if (e.getMessage().startsWith("/stop")) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("lobby");
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
				}
		}
		
		String[] message = e.getMessage().split(" ");
		
		if (message[0].length() == 5) {
			
			switch(message[0]) {

				case "/rpll":
					e.setMessage(e.getMessage().replace("/rpll", "/tpll"));
					break;
				case "/fpll":
					e.setMessage(e.getMessage().replace("/fpll", "/tpll"));
					break;
				case "/ypll":
					e.setMessage(e.getMessage().replace("/ypll", "/tpll"));
					break;
				case "/gpll":
					e.setMessage(e.getMessage().replace("/gpll", "/tpll"));
					break;
				case "/tokk":
					e.setMessage(e.getMessage().replace("/tokk", "/tpll"));
					break;
				case "/t[;;":
					e.setMessage(e.getMessage().replace("/t[;;", "/tpll"));
					break;
				case "/y[;;":
					e.setMessage(e.getMessage().replace("/y[;;", "/tpll"));
					break;
				case "/rokk":
					e.setMessage(e.getMessage().replace("/rokk", "/tpll"));
					break;
				case "/r[;;":
					e.setMessage(e.getMessage().replace("/r[;;", "/tpll"));
					break;
			}
			
		}
		
	}
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent c) {
		
		if (c.getMessage().startsWith("tpll ")) {
			Bukkit.getScheduler().runTask (Main.getInstance(), () -> c.getPlayer().performCommand(c.getMessage()));
			c.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void serverCommand(ServerCommandEvent s) {
		if (s.getCommand().startsWith("stop")) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("lobby");
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
				}
		}
	}
}
