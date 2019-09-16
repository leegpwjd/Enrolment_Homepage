package org.university.model;

import java.sql.Date;

public class Lecture {
	private String lCode;
	private String lName;
	private String department;
	private int grade;
	private String lRoom;
	private String lSubject;
	private int sCount;
	private String pName;
	private String start_time;
	private String end_time;
	private String college;

	
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getlCode() {
		return lCode;
	}

	public void setlCode(String lCode) {
		this.lCode = lCode;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getlRoom() {
		return lRoom;
	}

	public void setlRoom(String lRoom) {
		this.lRoom = lRoom;
	}

	public String getlSubject() {
		return lSubject;
	}

	public void setlSubject(String lSubject) {
		this.lSubject = lSubject;
	}

	public int getsCount() {
		return sCount;
	}

	public void setsCount(int sCount) {
		this.sCount = sCount;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "Lecture [lCode=" + lCode + ", lName=" + lName + ", department=" + department + ", grade=" + grade
				+ ", lRoom=" + lRoom + ", lSubject=" + lSubject + ", sCount=" + sCount + ", pName=" + pName
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", college=" + college + "]";
	}



	
	

}
