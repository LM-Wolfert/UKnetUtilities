package me.elgamer.UKnetUtilities.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class nv implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Check is command sender is a player
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Only players can do this!");
			return true;
		}

		//Convert sender to player
		Player p = (Player) sender;
		
		if (!(p.hasPermission("ukutils.nv"))) {
			p.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return true;
		}
		
		if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
			p.sendMessage(ChatColor.GREEN + "Night vision disabled!");
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		} else { 
			p.sendMessage(ChatColor.GREEN + "Night vision enabled!");
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
		}
		
		return true;
	}
}
