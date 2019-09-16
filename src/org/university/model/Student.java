package org.university.model;

public class Student {
	private String sid;
	private String spw;
	private String sname;
	private int age;
	private String address;
	private String gender;
	private String smajor;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSpw() {
		return spw;
	}

	public void setSpw(String spw) {
		this.spw = spw;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSmajor() {
		return smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", spw=" + spw + ", sname=" + sname + ", age=" + age + ", address=" + address
				+ ", gender=" + gender + ", smajor=" + smajor + "]";
	}

}
