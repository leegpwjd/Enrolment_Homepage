package org.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.university.model.Lecture;

public class ListDAO {
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String USER = "enrolment";
	private static final String PW = "1234";

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

	public boolean list(String lCode, String id) {
		Connection conn = getConnection();
		
		if (conn == null) {
			return false;
		}
		if(Overlap(lCode, id)) {
			return false;
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into s_enrolment " + "(sid, lcode) " + "values " + "(?, ?)";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);
			stmt.setString(2, lCode);

			int result = stmt.executeUpdate();
			
			if (result == 1) {
				Count(lCode);
				return true;
				
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return false;
	}
	
	public boolean Overlap(String lCode, String id) {
		Connection conn = getConnection();

		if (conn == null) {
			return false;
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select sid " + 
				"from s_enrolment " + 
				"where lcode = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, lCode);
			
			String sId = null;
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				sId = rs.getString(1);
			}
			
			if(id.equals(sId)) {
				return true;
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources(conn, stmt, rs);
		}
		return false;
	}
	
	public boolean Delete(String lCode, String id) {
		Connection conn = getConnection();

		if (conn == null) {
			return false;
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "delete " + 
				"from s_enrolment " + 
				"where sid = ? and lcode = ?";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);
			stmt.setString(2, lCode);

			int result = stmt.executeUpdate();
			
			if (result == 1) {
				Count(lCode);
				return true;
			} else {
				return false;
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return false;
	}
	
	public void Count(String lCode) {
		Connection conn = getConnection();
		System.out.println("들어왔다");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "update lecture " + 
				"set scount = (select COUNT(*) " + 
				"from s_enrolment " + 
				"where lcode = ?) " + 
				"where lcode = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, lCode);
			stmt.setString(2, lCode);
			
			int result = stmt.executeUpdate();
			if (result == 1) {
				System.out.println("된다");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Lecture[] selectLecture(String sId) {
		Lecture lecture = new Lecture();
	
		Connection conn = getConnection();
		String sql = "";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {		
				sql = "select * " + 
						"from lecture " + 
						"where lcode in (select lcode " + 
										"from s_enrolment " + 
										"where sid = ?)";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, sId);
			
			rs = stmt.executeQuery();
			
			List<Lecture> lists = new ArrayList<>();
			while(rs.next()) {
				Lecture l = new Lecture();
				l.setlCode(rs.getString(1));
				l.setlName(rs.getString(2));
				l.setDepartment(rs.getString(3));
				l.setGrade(rs.getInt(4));
				l.setlRoom(rs.getString(5));
				l.setlSubject(rs.getString(6));
				l.setsCount(rs.getInt(7));
				l.setpName(rs.getString(8));
				l.setStart_time(rs.getString(9));
				l.setEnd_time(rs.getString(10));
				
				lists.add(l);
				
			}
			
			return lists.toArray(new Lecture[0]);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
