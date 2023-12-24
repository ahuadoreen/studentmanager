package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseDataNew<T> {
    @Schema(example = "OK")
    private String message;
    @Schema(example = "200")
    private int code;
    private T data;

    public ResponseDataNew() {

    }

    public void ok() {
        setCode(200);
        setMessage("OK");
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

}
