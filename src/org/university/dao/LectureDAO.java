package org.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.university.model.Lecture;

public class LectureDAO {
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
	
	public Lecture[] selectLecture(String department, String grade, String lsubject) {
		Lecture lecture = new Lecture();
	
		Connection conn = getConnection();
		String sql = "";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			if(department.equals("전공과목")) {
				sql = "select * " + 
					"from lecture " + 
					"where department = ? and grade = ? and lsubject = ?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, department);
				stmt.setInt(2, Integer.parseInt(grade));
				stmt.setString(3, lsubject);
			
			}else {
				sql = "select * " +
					"from lecture " +
					"where department = ?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, department);
			}
			rs = stmt.executeQuery();
			
			List<Lecture> list = new ArrayList<>();
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
				
				list.add(l);
				
			}
			return list.toArray(new Lecture[0]);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
