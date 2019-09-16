package org.university.model;

import java.util.Arrays;

public class Schedule {
	private String lName;
	private String lRomm;
	private String totalTimes;
	private String weeks;
	private String[] time = new String[4];

	public String getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(String totalTimes) {
		this.totalTimes = totalTimes;
		System.out.println(totalTimes);
		setWeeks(totalTimes);
		setTime();
		
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getlRomm() {
		return lRomm;
	}

	public void setlRomm(String lRomm) {
		this.lRomm = lRomm;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String totalTimes) {
		String[] token = totalTimes.split("-");
		this.weeks = token[0];
	}

	public String[] getTime() {
		return time;
	}

	public void setTime() {
		String[] token = new String[4];
		token = totalTimes.split("-");

		this.time[0] = token[1];
		this.time[1] = token[3];
	}

}
