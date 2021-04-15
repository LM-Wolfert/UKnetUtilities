package me.elgamer.UKnetUtilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.elgamer.UKnetUtilities.commands.ll;
import me.elgamer.UKnetUtilities.commands.nv;
import me.elgamer.UKnetUtilities.listeners.CommandPreProcess;


public class Main extends JavaPlugin {

	static Main instance;
	static FileConfiguration config;
	
	public static ItemStack gui;
	
	@Override
	public void onEnable() {

		//Config Setup
		Main.instance = this;
		Main.config = this.getConfig();

		saveDefaultConfig();

		//Checking which functions to enable based on the server of the server
		//Tpll is checked via the CommandPreProcess rather than an actual command
		//This is to prevent it blocking the /tpll that is already existing on the earth server.
		if (config.getString("server_name").equals("Earth")) {
			
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());

		} else if (config.getString("server_name").equals("Building")) {
			
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			
			new CommandPreProcess(this);

		} else if (config.getString("server_name").equals("Lobby")) {

			//Create gui item				
			gui = new ItemStack(Material.COMPASS);
			ItemMeta meta = gui.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Navigation Menu");
			gui.setItemMeta(meta);
			
			//1 second timer.
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				public void run() {

					for (Player p : Bukkit.getOnlinePlayers()) {
						
						p.getInventory().setItem(5, gui);

					}

				}
			}, 0L, 20L);
		} else if (config.getString("server_name").equals("Minigames")) {

		} else if (config.getString("server_name").equals("Communitybuild")) {
			
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			
			new CommandPreProcess(this);
			
		} else {
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			new CommandPreProcess(this);
		}

	}
	
	public static Main getInstance() {
		return instance;
	}
		
}
