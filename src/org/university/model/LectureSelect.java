package org.university.model;

import java.sql.Date;

public class LectureSelect {

	private String department;
	private int grade;
	private String lSubject;
	private String college;

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

	public String getlSubject() {
		return lSubject;
	}

	public void setlSubject(String lSubject) {
		this.lSubject = lSubject;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "LectureSelect [department=" + department + ", grade=" + grade + ", lSubject=" + lSubject + ", college="
				+ college + "]";
	}

}
