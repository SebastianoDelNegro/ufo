package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testdb {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			String sql ="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' ";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			List<String> formeufo = new ArrayList<String>(); 
			while(res.next()) {
				String forma = res.getString("shape");
				formeufo.add(forma);
			}
			
			
			String sql2 = "SELECT COUNT(*) AS cnt "
					+ "FROM sighting "
					+ "WHERE shape = ?";
			String scelta = "circle";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, scelta);
			ResultSet res2 = st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			
			st2.close();
			System.out.println("ufo di forma " +scelta+" sono " + count);
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
