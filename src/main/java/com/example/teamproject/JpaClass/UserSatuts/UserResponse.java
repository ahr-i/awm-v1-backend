package com.example.teamproject.JpaClass.UserSatuts;

public class UserResponse {

    private String status;
    private String message;

    public UserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
