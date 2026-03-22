package com.embarkx.firstspringboot.Model;

public class helloResponse {
    private String message;

    public helloResponse(String helloWorld) {
        message=helloWorld;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
