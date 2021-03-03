package me.elgamer.UKnetUtilities;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.elgamer.UKnetUtilities.commands.ll;
import me.elgamer.UKnetUtilities.commands.nv;
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
			getCommand("nv").setExecutor(new nv());

		} else if (config.getString("server_name").equals("Building")) {
			getCommand("tpll").setExecutor(new Tpll());
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());

		} else if (config.getString("server_name").equals("Lobby")) {
			
		} else if (config.getString("server_name").equals("Minigames")) {
			
		} else {
			getCommand("tpll").setExecutor(new Tpll());
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
		}
	}

}
