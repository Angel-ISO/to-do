package project.task.Utils;

import project.task.dto.UserDto;

public class LoginResponse {
    private String message;
    private UserDto user;


    public LoginResponse(String message, UserDto user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
