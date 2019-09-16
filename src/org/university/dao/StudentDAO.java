package org.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.university.model.Student;

public class StudentDAO {
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

	public boolean addStudent(Student student) {
		Connection conn = getConnection();

		if (conn == null) {
			return false;
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into students " + "(sid, spw, sname, age, address, gender, smajor) " + "values "
				+ "(?, ?, ?, ?, ?, ?, ?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getSid());
			stmt.setString(2, student.getSpw());
			stmt.setString(3, student.getSname());
			stmt.setInt(4, student.getAge());
			stmt.setString(5, student.getAddress());
			stmt.setString(6, student.getGender());
			stmt.setString(7, student.getSmajor());

			int result = stmt.executeUpdate();
			if (result == 1) {
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

	public boolean LoginTestStudent(String sid, String spw) {
		Student student = new Student();
		Connection conn = getConnection();

		if (conn == null) {
			return false;
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select spw " + "from students " + "where sid = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sid);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (spw.equals(rs.getString(1))) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return false;
	}

}
