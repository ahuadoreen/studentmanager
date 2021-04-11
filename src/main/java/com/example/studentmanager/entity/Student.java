package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Student {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "1")
    private String sno;

    @Schema(example = "李磊")
    private String name;

    private Classes classes;

    @Schema(example = "1")
    private Integer gender;

    @Schema(example = "15")
    @NotNull(message="年龄不能为空")
    @Min(value = 7, message = "学生年龄不能小于7岁")
    @Max(value = 18, message = "学生年龄不能大于18岁")
    private Integer age;

    private Long classId;

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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
