package me.elgamer.UKnetUtilities.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.elgamer.UKnetUtilities.Main;

public class Announcements {

	public static void newAnnouncement(String message, String colourEnum) {

		try (Connection conn = Main.getInstance().dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(
				"INSERT INTO announcements(message, colour) VALUES(?, ?);"
				)){

			statement.setString(1, message);
			statement.setString(2, colourEnum);
			statement.executeUpdate();

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
}
