package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.elgamer.UKnetUtilities.Main;
import me.elgamer.UKnetUtilities.gui.NavigationGUI;


public class InventoryClicked implements Listener {
	
	public InventoryClicked(Main plugin) {
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if (e.getCurrentItem() == null) {
			return;
		}
		
		String title = e.getView().getTitle();
		
		if (title.equals(NavigationGUI.inventory_name)) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null){
				return;
			}
			if (title.equals(NavigationGUI.inventory_name)) {
				NavigationGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
			}
		}
	}

}
