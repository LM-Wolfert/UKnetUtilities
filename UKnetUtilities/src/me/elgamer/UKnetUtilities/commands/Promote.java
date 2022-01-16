package me.elgamer.UKnetUtilities.commands;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.elgamer.UKnetUtilities.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;

public class Promote implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Check is command sender is a player
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (!p.hasPermission("ukutils.promote")) {
				p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}

		}

		if (args.length < 2) {
			help(sender);
			return true;
		}

		if (!(args[1].equals("apprentice") || args[1].equals("jrbuilder") || args[1].equals("builder"))) {
			help(sender);
			return true;
		}

		LuckPerms lp = Main.getLuckPerms();
		UserManager userManager = lp.getUserManager();

		try {
			CompletableFuture<UUID> uuidFuture = userManager.lookupUniqueId(args[0]);

			uuidFuture.thenAcceptAsync(uuid -> {
				if (uuid==null) {
					sender.sendMessage(ChatColor.RED + args[0] + " has not connected to the server, this command is case sensitive.");
				} else {
					CompletableFuture<User> userFuture = userManager.loadUser(uuid);
					userFuture.thenAcceptAsync(user -> {
						String group = user.getPrimaryGroup();

						if (!(group.equalsIgnoreCase("default") || group.equalsIgnoreCase("applicant") || group.equalsIgnoreCase("apprentice") || group.equalsIgnoreCase("jrbuilder"))) {
							sender.sendMessage(ChatColor.RED + args[0] + " is already a Builder or higher.");
						} else {

							Node primary = Node.builder("group." + group).build();
							Node newGroup = Node.builder("group." + args[1]).build();

							switch (args[1]) {

							case "builder":			
								user.data().add(newGroup);
								user.data().remove(primary);
								sender.sendMessage(ChatColor.GREEN + args[0] + " has been promoted to Builder.");
								break;
							case "jrbuilder":
								if (group.equalsIgnoreCase("jrbuilder")) {
									sender.sendMessage(ChatColor.RED + args[0] + " is already a Jr.Builder.");
								} else {
									user.data().add(newGroup);
									user.data().remove(primary);
									sender.sendMessage(ChatColor.GREEN + args[0] + " has been promoted to Jr.Builder.");
								}
								break;
							case "apprentice":
								if (group.equalsIgnoreCase("jrbuilder") || group.equalsIgnoreCase("apprentice")) {
									sender.sendMessage(ChatColor.RED + args[0] + " is already an Apprentice or higher.");
								} else {
									user.data().add(newGroup);
									user.data().remove(primary);
									sender.sendMessage(ChatColor.GREEN + args[0] + " has been promoted to Apprentice.");
								}
								break;
							}

							userManager.saveUser(user);
						}
					});
				}
			});

		} catch (IllegalArgumentException e) {
			sender.sendMessage(ChatColor.RED + args[0] + " has not connected to the server, this command is case sensitive.");
			return true;
		}	

		return true;
	}

	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "/promote <user> apprentice/jrbuilder/builder");
	}
}
