package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class GradeCourse {
    @Schema(example = "1")
    private Integer grade;
    @Schema(example = "1,2,3")
    private String subjectIds;
    @Schema(example = "语文,数学,英语")
    private String subjectNames;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(String subjectNames) {
        this.subjectNames = subjectNames;
    }

}
