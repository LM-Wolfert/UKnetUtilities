package me.elgamer.UKnetUtilities;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.elgamer.UKnetUtilities.commands.FakeCommandRegistry;
import me.elgamer.UKnetUtilities.commands.ll;
import me.elgamer.UKnetUtilities.commands.nv;
import me.elgamer.UKnetUtilities.gui.NavigationGUI;
import me.elgamer.UKnetUtilities.listeners.InventoryClicked;
import me.elgamer.UKnetUtilities.listeners.PlayerInteract;
import me.elgamer.UKnetUtilities.utils.Backup;

public class Main extends JavaPlugin {

	static Main instance;
	static FileConfiguration config;
	
	public static ItemStack gui;
	
	public final static long hour = 20*60*60;
	public long time;
	
	@Override
	public void onEnable() {

		//Config Setup
		Main.instance = this;
		Main.config = this.getConfig();

		saveDefaultConfig();
		
		time = hour*config.getLong("backup_interval");
		
		//Checking which functions to enable based on the server of the server
		//Tpll is checked via the CommandPreProcess rather than an actual command
		//This is to prevent it blocking the /tpll that is already existing on the earth server.
		if (config.getString("server_name").equals("Earth")) {
			
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			
			//x hour timer, configurable in config.
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				public void run() {

					Backup.runBackup();
					
				}
			}, 0L, time);

		} else if (config.getString("server_name").equals("Building")) {
			
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			
			try {
	            FakeCommandRegistry.registerFakeCommand(new FakeCommandRegistry("tpll"), this);
	        } catch (NoSuchMethodException | SecurityException
	                | IllegalAccessException | IllegalArgumentException
	                | InvocationTargetException e) {
	            e.printStackTrace();
	        } catch (ReflectiveOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (config.getString("server_name").equals("Lobby")) {

			//Create gui item				
			gui = new ItemStack(Material.NETHER_STAR);
			ItemMeta meta = gui.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Navigation Menu");
			gui.setItemMeta(meta);
			
			//Listeners
			new PlayerInteract(this, gui);
			new InventoryClicked(this);
			
			//GUI
			NavigationGUI.initialize();
			
			//Bungeecord
			this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
			
			//1 second timer.
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				public void run() {

					for (Player p : Bukkit.getOnlinePlayers()) {
						
						p.getInventory().setItem(4, gui);

					}

				}
			}, 0L, 20L);
		} else if (config.getString("server_name").equals("Minigames")) {

		} else if (config.getString("server_name").equals("Communitybuild")) {
			
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			
			try {
	            FakeCommandRegistry.registerFakeCommand(new FakeCommandRegistry("tpll"), this);
	        } catch (NoSuchMethodException | SecurityException
	                | IllegalAccessException | IllegalArgumentException
	                | InvocationTargetException e) {
	            e.printStackTrace();
	        } catch (ReflectiveOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//x hour timer, configurable in config.
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				public void run() {

					Backup.runBackup();
					
				}
			}, 0L, time);

		} else {
			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());
			
			try {
	            FakeCommandRegistry.registerFakeCommand(new FakeCommandRegistry("tpll"), this);
	        } catch (NoSuchMethodException | SecurityException
	                | IllegalAccessException | IllegalArgumentException
	                | InvocationTargetException e) {
	            e.printStackTrace();
	        } catch (ReflectiveOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public static Main getInstance() {
		return instance;
	}
		
}
