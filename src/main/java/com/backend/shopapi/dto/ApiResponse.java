package com.backend.shopapi.dto;

import java.util.Map;

public class ApiResponse {
    private String message;
    private int code;
    private Map<String, String> errors;

    public ApiResponse() {
    }

    public ApiResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ApiResponse(String message, int code, Map<String, String> errors) {
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
