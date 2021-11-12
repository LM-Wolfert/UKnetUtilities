package me.elgamer.UKnetUtilities.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.elgamer.UKnetUtilities.Main;
import me.elgamer.UKnetUtilities.utils.CreateItem;

public class NavigationGUI {
	
	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 3 * 9;
	
	public static void initialize() {
		inventory_name = ChatColor.AQUA + "" + ChatColor.BOLD + "Navigation Menu";
		
		inv = Bukkit.createInventory(null, inv_rows);
		
	}
	
	public static Inventory GUI (Player p) {
		
		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
		
		inv.clear();
		
		CreateItem.createItem(inv, Material.DIAMOND_BLOCK, 1, 12, ChatColor.AQUA + "" + ChatColor.BOLD + "Earth",
				ChatColor.WHITE + "Teleport to the earth server.",
				ChatColor.WHITE + "If you wish to build you must be Jr.Builder+",
				ChatColor.WHITE + "It is recommended to use the modpack for building.",
				ChatColor.WHITE + "Supports 1.12.2 - 1.17 without the modpack.");		
		CreateItem.createItem(inv, Material.BRICK, 1, 14, ChatColor.AQUA + "" + ChatColor.BOLD + "Building",
				ChatColor.WHITE + "Teleport to the building server.",
				ChatColor.WHITE + "No requirements to start building.",
				ChatColor.WHITE + "Recommended version is 1.16.5.",
				ChatColor.WHITE + "Supports 1.12.2 - 1.17");	
		CreateItem.createItem(inv, Material.PUMPKIN, 1, 16, ChatColor.AQUA + "" + ChatColor.BOLD + "Minigames",
				ChatColor.WHITE + "Teleport to the minigames server.",
				ChatColor.WHITE + "Currenly Closed!",
				ChatColor.WHITE + "Supports 1.12.2 - 1.16.5.");	
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		
		
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "" + ChatColor.BOLD + "Earth")) {
						
			//Will open the build location gui.
			p.closeInventory();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("earth");

			p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
		} else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "" + ChatColor.BOLD + "Building")) {
			p.closeInventory();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("building");
			p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
		} else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "" + ChatColor.BOLD + "Minigames")) {
			p.sendMessage(ChatColor.RED + "Closed until further notice!");
			/*
			p.closeInventory();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("minigames");

			p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
			*/
		} 
	}


}
