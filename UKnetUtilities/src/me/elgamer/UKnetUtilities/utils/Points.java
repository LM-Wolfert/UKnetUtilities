package me.elgamer.UKnetUtilities.utils;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.elgamer.UKnetUtilities.Main;


public class Points {
/*
	Main instance = Main.getInstance(); 
	
	public Leaderboard getOrderedPoints(String uuid, Leaderboard lead) {

		try {
			PreparedStatement statement = instance.getConnection().prepareStatement
					("SELECT * FROM " + instance.pointsData + " ORDER BY POINTS DESC");
			ResultSet results = statement.executeQuery();
			int pos = 0;
			for (int i = 0; i < 9; i++) {

				if (results.next()) {
					pos += 1;
					lead.position[i] = pos;
					lead.uuids[i] = results.getString("UUID");
					lead.points[i] = results.getInt("POINTS");
				} else {
					return lead;
				}

			}

			return lead;

		} catch (SQLException e) {
			e.printStackTrace();
			return lead;
		}
	}

	public Leaderboard getPoints(String uuid, Leaderboard lead) {

		try {
			PreparedStatement statement = instance.getConnection().prepareStatement
					("SELECT * FROM " + instance.pointsData + " ORDER BY POINTS DESC");
			ResultSet results = statement.executeQuery();
			int pos = 0;

			while (results.next()) {
				pos += 1;
				if (results.getString("UUID").equals(uuid)) {
					break;
				}
			}

			for (int j = 0; j < 5; j++) {
				pos -= 1;
				if (results.previous()) {
				} else {
					break;
				}
			}

			for (int i = 0; i < 9; i++) {

				if (results.next()) {
					pos += 1;
					lead.position[i] = pos;
					lead.uuids[i] = results.getString("UUID");
					lead.points[i] = results.getInt("POINTS");
				} else {
					return lead;
				}

			}

			return lead;

		} catch (SQLException e) {
			e.printStackTrace();
			return lead;
		}
	}
*/
}
