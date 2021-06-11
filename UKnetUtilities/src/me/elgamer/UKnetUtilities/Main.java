package me.elgamer.UKnetUtilities;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
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

	public ItemStack slot5;
	int hourTime;
	int minuteTime;
	int secondTime;
	int i;

	@Override
	public void onEnable() {

		//Config Setup
		Main.instance = this;
		Main.config = this.getConfig();

		saveDefaultConfig();
		time = hour*config.getLong("backup_interval");

		String restartTimes = config.getString("restart_time");
		String[] restart = restartTimes.split(",");
		
		String backupTimes = config.getString("backup_time");
		String[] backups = backupTimes.split(",");

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

		//1 minute timer
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {		
			public void run() {

				//Restart check
				LocalDateTime timeZone = LocalDateTime.now(ZoneId.of("Europe/London"));
				minuteTime = timeZone.getMinute();
				
				if (minuteTime == 58) {

					for (String s : restart) {

						try {

							i = Integer.parseInt(s);
							hourTime = timeZone.getHour();

							if (i == 0) {

								if (hourTime == 23 && i == 0) {

									secondTime = timeZone.getSecond();

									Bukkit.getScheduler().runTaskLater (instance, () -> Bukkit.dispatchCommand(console, "say The server is restarting in 1 minute!") , (60 - secondTime)*20); //20 ticks equal 1 second
									Bukkit.getScheduler().runTaskLater (instance, () -> Bukkit.dispatchCommand(console, "stop") , (60 - secondTime + 60)*20); //20 ticks equal 1 second
								}

							} else {

								if (hourTime == i-1) {

									secondTime = timeZone.getSecond();

									Bukkit.getScheduler().runTaskLater (instance, () -> Bukkit.dispatchCommand(console, "say The server is restarting in 1 minute!") , (60 - secondTime)*20); //20 ticks equal 1 second
									Bukkit.getScheduler().runTaskLater (instance, () -> Bukkit.dispatchCommand(console, "stop") , (60 - secondTime + 60)*20); //20 ticks equal 1 second

								}

							}


						} catch (NumberFormatException e) {
							e.printStackTrace();
						}

					}

				}
			}		
		}, 0L, 1200L);

		//Checking which functions to enable based on the server of the server
		//Tpll is checked via the CommandPreProcess rather than an actual command
		//This is to prevent it blocking the /tpll that is already existing on the earth server.
		if (config.getString("server_name").equals("Earth")) {

			getCommand("ll").setExecutor(new ll());
			getCommand("nv").setExecutor(new nv());

			//1 minute timer
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {		
				public void run() {

					//Backup check
					LocalDateTime timeZone = LocalDateTime.now(ZoneId.of("Europe/London"));
					minuteTime = timeZone.getMinute();
					
					if (minuteTime == 59) {

						for (String s : backups) {

							try {

								i = Integer.parseInt(s);
								hourTime = timeZone.getHour();

								if (i == 0) {

									if (hourTime == 23 && i == 0) {

										secondTime = timeZone.getSecond();

										Bukkit.getScheduler().runTaskLater (instance, () -> Backup.runBackup() , (60 - secondTime)*20); //20 ticks equal 1 second
									}

								} else {

									if (hourTime == i-1) {

										secondTime = timeZone.getSecond();

										Bukkit.getScheduler().runTaskLater (instance, () -> Backup.runBackup() , (60 - secondTime)*20); //20 ticks equal 1 second

									}

								}


							} catch (NumberFormatException e) {
								e.printStackTrace();
							}

						}

					}
				}		
			}, 0L, 1200L);

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

						slot5 = p.getInventory().getItem(4);

						if (!(slot5 == null)) {
							if (slot5.equals(gui)) {

							} else {
								p.getInventory().setItem(4, gui);
							}
						} else {
							p.getInventory().setItem(4, gui);
						}

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

			//1 minute timer
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {		
				public void run() {

					//Backup check
					LocalDateTime timeZone = LocalDateTime.now(ZoneId.of("Europe/London"));
					minuteTime = timeZone.getMinute();
					
					if (minuteTime == 59) {

						for (String s : backups) {

							try {

								i = Integer.parseInt(s);
								hourTime = timeZone.getHour();

								if (i == 0) {

									if (hourTime == 23 && i == 0) {

										secondTime = timeZone.getSecond();

										Bukkit.getScheduler().runTaskLater (instance, () -> Backup.runBackup() , (60 - secondTime)*20); //20 ticks equal 1 second
									}

								} else {

									if (hourTime == i-1) {

										secondTime = timeZone.getSecond();

										Bukkit.getScheduler().runTaskLater (instance, () -> Backup.runBackup() , (60 - secondTime)*20); //20 ticks equal 1 second

									}

								}


							} catch (NumberFormatException e) {
								e.printStackTrace();
							}

						}

					}
				}		
			}, 0L, 1200L);

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
