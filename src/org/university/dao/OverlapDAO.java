package org.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class OverlapDAO {
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String USER = "enrolment";
	private static final String PW = "1234";

	String start_time = "";
	String end_time = "";
	
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(URL, USER, PW);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void check(String lcode) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select start_time, end_time " + 
				"from lecture " + 
				"where lcode = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, lcode);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				start_time = rs.getString(1);
				end_time = rs.getString(2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean overlap(String sId, String lcode) {
		check(lcode);

		Connection conn = getConnection();
		String sql = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

				sql = "select start_time, end_time " + 
						"from lecture " + 
						"where lcode in (select lcode " + 
						"from s_enrolment " + 
						"where sid = ?)";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1, sId);
				
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					if(start_time.equals(rs.getString(1)) || end_time.equals(rs.getString(2))){
						return true;
					}
				}
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources(conn, stmt, rs);
		}

		return false;
	}
	
	
	private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

