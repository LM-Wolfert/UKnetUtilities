package me.elgamer.UKnetUtilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.elgamer.UKnetUtilities.Main;
import me.elgamer.UKnetUtilities.gui.NavigationGUI;

public class PlayerInteract implements Listener {
	
	public PlayerInteract(Main plugin, ItemStack item) {

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

	}

	@EventHandler
	public void interactEvent(PlayerInteractEvent e) {
		
		if (e.getPlayer().getOpenInventory().getType() != InventoryType.CRAFTING && e.getPlayer().getOpenInventory().getType() != InventoryType.CREATIVE) {
		    return;
		}
		
		if (e.getPlayer().getInventory().getItemInMainHand().equals(Main.gui)) {
			e.setCancelled(true);
			e.getPlayer().openInventory(NavigationGUI.GUI(e.getPlayer()));
		}
	}

}
