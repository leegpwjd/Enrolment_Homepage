package org.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.university.model.Schedule;

public class ScheduleDAO {
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String USER = "enrolment";
	private static final String PW = "1234";

	private String[][] Mons = new String[8][2];
	private String[][] Tues = new String[8][2];
	private String[][] Weds = new String[8][2];
	private String[][] Thus = new String[8][2];
	private String[][] Fris = new String[8][2];
	
	public String[][] getMons() {
		return Mons;
	}

	public void setMons(String[][] mons) {
		Mons = mons;
	}

	public String[][] getTues() {
		return Tues;
	}

	public void setTues(String[][] tues) {
		Tues = tues;
	}

	public String[][] getWeds() {
		return Weds;
	}

	public void setWeds(String[][] weds) {
		Weds = weds;
	}

	public String[][] getThus() {
		return Thus;
	}

	public void setThus(String[][] thus) {
		Thus = thus;
	}

	public String[][] getFris() {
		return Fris;
	}

	public void setFris(String[][] fris) {
		Fris = fris;
	}

	Schedule s = new Schedule();

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

	public void schedule(String id) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select lname, lroom, start_time, end_time " + "from lecture " + "where lcode IN (select lcode "
				+ "from s_enrolment " + "where sid = ?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			rs = stmt.executeQuery();

			while (rs.next()) {
				s.setlName(rs.getString(1));
				s.setlRomm(rs.getString(2));
				s.setTotalTimes(rs.getString(3) + "-" + rs.getString(4));
				makeSchedule();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void makeSchedule() {
		System.out.println(s.getWeeks());
		if (s.getWeeks().equals("MON")) {
			for (int idx = 1; idx < 9; idx++) {

				if (Integer.parseInt(s.getTime()[0]) == idx) {
					Mons[idx][0] = s.getlName();
					Mons[idx][1] = s.getlRomm();
				} else if (Integer.parseInt(s.getTime()[1]) == idx) {
					Mons[idx][0] = s.getlName();
					Mons[idx][1] = s.getlRomm();
				}
			}
		} else if (s.getWeeks().equals("TUE")) {
			for (int idx = 1; idx < 9; idx++) {
				if (Integer.parseInt(s.getTime()[0]) == idx) {
					Tues[idx][0] = s.getlName();
					Tues[idx][1] = s.getlRomm();
				} else if (Integer.parseInt(s.getTime()[1]) == idx) {
					Tues[idx][0] = s.getlName();
					Tues[idx][1] = s.getlRomm();
				}
			}
		} else if (s.getWeeks().equals("WED")) {
			for (int idx = 1; idx < 9; idx++) {
				if (Integer.parseInt(s.getTime()[0]) == idx) {
					Weds[idx][0] = s.getlName();
					Weds[idx][1] = s.getlRomm();
				} else if (Integer.parseInt(s.getTime()[1]) == idx) {
					Weds[idx][0] = s.getlName();
					Weds[idx][1] = s.getlRomm();
				}
			}
		} else if (s.getWeeks().equals("THU")) {
			for (int idx = 1; idx < 9; idx++) {
				if (Integer.parseInt(s.getTime()[0]) == idx) {
					Thus[idx][0] = s.getlName();
					Thus[idx][1] = s.getlRomm();
				} else if (Integer.parseInt(s.getTime()[1]) == idx) {
					Thus[idx][0] = s.getlName();
					Thus[idx][1] = s.getlRomm();
				}
			}
		} else if (s.getWeeks().equals("FRI")) {
			for (int idx = 1; idx < 9; idx++) {
				if (Integer.parseInt(s.getTime()[0]) == idx) {
					Fris[idx][0] = s.getlName();
					Fris[idx][1] = s.getlRomm();
				} else if (Integer.parseInt(s.getTime()[1]) == idx) {
					Fris[idx][0] = s.getlName();
					Fris[idx][1] = s.getlRomm();
				}
			}
		}
	}
}
