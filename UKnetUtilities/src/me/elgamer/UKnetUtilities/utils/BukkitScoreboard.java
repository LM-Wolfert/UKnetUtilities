package me.elgamer.UKnetUtilities.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class BukkitScoreboard {

	ScoreboardManager manager;
	Scoreboard board;
	
	Points points;
	Weekly weekly;

	String entry;

	public BukkitScoreboard() {
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		
		points = new Points();
		weekly = new Weekly();
	}

	public void pointsScoreboard(Player p) {
		Objective objective = board.registerNewObjective("test", "dummy", "Test Scoreboard");

	}

	public void pointsTop(Player p) {

		Leaderboard lead = points.getOrderedPoints(p.getUniqueId().toString(), new Leaderboard());
		
	}

	public void pointsWeekly() {

	}

	public void pointsWeeklyTop() {

	}

}
