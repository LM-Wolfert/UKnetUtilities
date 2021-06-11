package me.elgamer.UKnetUtilities.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import me.elgamer.UKnetUtilities.Main;

public class Backup {
	
	public static void runBackup() {
		
		Main instance = Main.getInstance();
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		
		//20 ticks equal 1 second
		final long second = 20;
		
		console.sendMessage("Starting Backup");
		Bukkit.dispatchCommand(console, "say Starting Backup");
		
		Bukkit.dispatchCommand(console, "save-off");
		
		Bukkit.getScheduler().runTaskLater (instance, () -> 
		Bukkit.dispatchCommand(console, "save-all flush"), second);
		
		Bukkit.getScheduler().runTaskLater (instance, () -> 
		Bukkit.dispatchCommand(console, "scriptexecute exec backup"), 5*second);
		
		Bukkit.getScheduler().runTaskLater (instance, () -> 
		Bukkit.dispatchCommand(console, "save-on"), 90*second);
		
		Bukkit.getScheduler().runTaskLater (instance, () -> 
		Bukkit.dispatchCommand(console, "say Backup Finished"), 90*second);
		
	}

}
