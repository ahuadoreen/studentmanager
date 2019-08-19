package com.example.studentmanager.entity;

public class Classes {

	private Long id;

	private String className;

	private Integer grade;

	private Teacher mainTeacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Teacher getMainTeacher() {
		return mainTeacher;
	}

	public void setMainTeacher(Teacher mainTeacher) {
		this.mainTeacher = mainTeacher;
	}

}
