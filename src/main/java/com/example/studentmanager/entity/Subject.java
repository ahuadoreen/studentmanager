package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class Subject {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "语文")
    private String name;

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
}
