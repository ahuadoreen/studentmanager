package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClassCourseTeacher {
    @Schema(example = "1")
    private Long classId;
    private Teacher teacher;
    private GradeCourse gradeCourse;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public GradeCourse getGradeCourse() {
        return gradeCourse;
    }

    public void setGradeCourse(GradeCourse gradeCourse) {
        this.gradeCourse = gradeCourse;
    }
}
