package hu.szalai.dominik.akasztofa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Adatbazis {
	
	private static boolean inicializalva=false;
	
	private static boolean nem_sikerult=false;
	
	private static int max_bejegyzes = 15;
	
	private static final String DB_URL = "jdbc:sqlite:pontok.db";
	
	private static void inicializal() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            // Create table if it doesn't exist
            String sql = "CREATE TABLE IF NOT EXISTS highscores (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "username VARCHAR(50), " +
                         "score INT, " +
                         "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
            stmt.execute(sql);
            inicializalva=true;
        } catch (SQLException e) {
        	nem_sikerult=true;
            //e.printStackTrace();
        }
    }
	
	public static List<AdatbazisBejegyzes> kerPontok() {
		
		if(!inicializalva) {
			inicializal();
		}
		
		if(!nem_sikerult) {
	        List<AdatbazisBejegyzes> scores = new ArrayList<>();
	        String sql = "SELECT username, score, timestamp FROM highscores " +
	                     "ORDER BY score DESC LIMIT "+max_bejegyzes;
	        try (Connection conn = DriverManager.getConnection(DB_URL);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                scores.add(new AdatbazisBejegyzes(
	                    rs.getString("username"),
	                    rs.getInt("score"),
	                    rs.getString("timestamp")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return scores;
		}else {
			return new ArrayList<AdatbazisBejegyzes>();
		}
    }
	
	public static void beszur(String username, int score) {
		if(!inicializalva) {
			inicializal();
		}
		if(!nem_sikerult) {
	        String sql = "INSERT INTO highscores (username, score) VALUES (?, ?)";
	        try (Connection conn = DriverManager.getConnection(DB_URL);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setInt(2, score);
	            pstmt.executeUpdate();
	            torolMaradekot();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
    }
	
	private static void torolMaradekot() {
        // Delete records not in the top 15 scores
        String sql = "DELETE FROM highscores WHERE id NOT IN (" +
                     "SELECT id FROM highscores ORDER BY score DESC LIMIT "+max_bejegyzes+")";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
