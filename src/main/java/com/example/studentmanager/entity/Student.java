package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class Student {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "1")
    private String sno;

    @Schema(example = "1")
    private String name;

    private Classes classes;

    @Schema(example = "1")
    private Integer gender;

    @Schema(example = "15")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
