package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class Teacher {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "李磊")
    private String name;
    @Schema(example = "1")
    private Integer gender;
    @Schema(example = "25")
    private Integer age;
    @Schema(example = "${serverPath}/images/75874714c6f547519d9f49682efba0e6.jpg")
    private String imageUrl;
    @Schema(example = "1,2,3")
    private String subjectIds;
    @Schema(example = "语文,数学,英语")
    private String subjectNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
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
