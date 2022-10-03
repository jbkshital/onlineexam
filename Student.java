package com.kiranacademy.onlineexam3;

public class Student {

	int rno,marks;

	@Override
	public String toString() {
		return "Student [rno=" + rno + ", marks=" + marks + "]";
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
