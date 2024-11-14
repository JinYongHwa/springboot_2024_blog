package kr.ac.mjc.blog.dto;

import kr.ac.mjc.blog.domain.User;

public class UserResponseDto {

    private boolean isSuccess;
    private String message;
    private User user;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
