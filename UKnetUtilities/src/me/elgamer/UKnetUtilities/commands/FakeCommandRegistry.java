package me.elgamer.UKnetUtilities.commands;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.elgamer.UKnetUtilities.projections.ModifiedAirocean;
import me.elgamer.UKnetUtilities.utils.LocationUtil;

public class FakeCommandRegistry extends BukkitCommand {

	public FakeCommandRegistry(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static void registerFakeCommand(Command whatCommand,Plugin plugin)
			throws ReflectiveOperationException {
		//Getting command map from CraftServer
		Method commandMap = plugin.getServer().getClass().getMethod("getCommandMap", null);
		//Invoking the method and getting the returned object (SimpleCommandMap)
		Object cmdmap = commandMap.invoke(plugin.getServer(), null);
		//getting register method with parameters String and Command from SimpleCommandMap
		Method register = cmdmap.getClass().getMethod("register", String.class,Command.class);
		//Registering the command provided above
		register.invoke(cmdmap, whatCommand.getName(),whatCommand);
		//All the exceptions thrown above are due to reflection, They will be thrown if any of the above methods
		//and objects used above change location or turn private. IF they do, let me know to update the thread!
	}

	@Override
	public boolean execute(CommandSender sender, String arg, String[] args) {

		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED+"Only players can do this!");
			return true;
		}

		Player p = (Player) sender;

		if (!(p.hasPermission("ukutils.tpll"))) {
			p.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return true;
		}

		if(args.length==0) {
			p.sendMessage(ChatColor.RED + "/tpll <lat> <lon>");
			return true;
		}

		String[] splitCoords = args[0].split(",");

		if(splitCoords.length==2&&args.length<3) { // lat and long in single arg
			args = splitCoords;
		}

		if(args[0].endsWith(",")) {
			args[0] = args[0].substring(0, args[0].length() - 1);
		}

		if(args.length>1&&args[1].endsWith(",")) {
			args[1] = args[1].substring(0, args[1].length() - 1);
		}

		if(args.length!=2) {
			p.sendMessage(ChatColor.RED + "/tpll <lat> <lon>");
			return true;
		}

		double lon, lat;

		try {
			lat = Double.parseDouble(args[0]);
			lon = Double.parseDouble(args[1]);
		} catch(Exception e) {
			p.sendMessage(ChatColor.RED + "/tpll <lat> <lon>");
			return true;
		}

		if (lat>90 || lat<-90) {
			p.sendMessage(ChatColor.RED + "Latitude is out of bounds, keep it between -90 and 90");
			return true;
		}

		if (lon>180 || lon<-180) {
			p.sendMessage(ChatColor.RED + "Longitude is out of bounds, keep it between -180 and 180");
			return true;
		}

		ModifiedAirocean projection = new ModifiedAirocean();

		double proj[] = projection.fromGeo(lon, lat);

		Location loc = null;

		final float pitch = p.getLocation().getPitch();
		final float yaw = p.getLocation().getYaw();

		try {

			loc = LocationUtil.getSafeDestination(new Location(p.getWorld(), proj[0], p.getWorld().getMaxHeight(), proj[1]));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loc = new Location(p.getWorld(), proj[0], loc.getY(), proj[1], yaw, pitch);

		if (loc.getY() == 0) {
			p.sendMessage(ChatColor.RED + "This location is above the void, you may not teleport here!");
			return true;
		}

		p.teleport(loc);
		p.sendMessage(ChatColor.GRAY + "Teleported " + p.getName() + " to " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ());

		return true;
	}



}
