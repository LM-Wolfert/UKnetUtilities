package me.elgamer.UKnetUtilities;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.elgamer.UKnetUtilities.commands.ll;
import net.md_5.bungee.api.ChatColor;
import me.elgamer.UKnetUtilities.commands.Tpll;

public class Main extends JavaPlugin {
	
	static Main instance;
	static FileConfiguration config;

	@Override
	public void onEnable() {

		//Config Setup
		Main.instance = this;
		Main.config = this.getConfig();

		saveDefaultConfig();
		
		//Checking which functions to enable based on the server of the server
		if (config.getString("server_name").equals("Earth")) {
			getCommand("ll").setExecutor(new ll());

		} else if (config.getString("server_name").equals("Building")) {
			getCommand("tpll").setExecutor(new Tpll());
			getCommand("ll").setExecutor(new ll());

		} else if (config.getString("server_name").equals("Lobby")) {
			
		} else if (config.getString("server_name").equals("Minigames")) {
			
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "No valid world name encountered, plugin will have no functions!");
		}
	}

}
