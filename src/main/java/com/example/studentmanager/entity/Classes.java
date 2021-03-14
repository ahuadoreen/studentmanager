package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class Classes {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "一班")
	private String className;

	@Schema(example = "1")
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
