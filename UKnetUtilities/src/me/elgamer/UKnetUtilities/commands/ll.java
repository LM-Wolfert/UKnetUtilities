package me.elgamer.UKnetUtilities.commands;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.elgamer.UKnetUtilities.projections.ModifiedAirocean;

public class ll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Check is command sender is a player
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Only players can do this!");
			return true;
		}

		//Convert sender to player
		Player p = (Player) sender;
		
		double x = p.getLocation().getX();
		double z = p.getLocation().getZ();

		if (!(p.hasPermission("ukutils.ll"))) {
			p.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return true;
		}

		ModifiedAirocean projection = new ModifiedAirocean();

		double proj[] = projection.toGeo(x, z);
	
		DecimalFormat df = new DecimalFormat("#.######");
		
		String lat = df.format(proj[1]);
		String lon = df.format(proj[0]);
		p.sendMessage(ChatColor.GRAY + "Your coordinates are " + lat + "," + lon);
		p.sendMessage(ChatColor.GRAY + "https://www.google.com/maps/@" + lat + "," + lon + ",80m/data=!3m1!1e3");
		
		return true;
	}
	
	

}
