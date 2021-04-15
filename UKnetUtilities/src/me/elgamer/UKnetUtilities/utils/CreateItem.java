package me.elgamer.UKnetUtilities.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {
	
	public static ItemStack createItem(Inventory inv, Material material, int amount, int invSlot, String displayName, String... loreString) {

		ItemStack item;

		List<String> lore = new ArrayList<String>();

		item = new ItemStack(material);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		for (String s : loreString) {
			lore.add(s);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(invSlot - 1,  item);

		return item;

	}

}
