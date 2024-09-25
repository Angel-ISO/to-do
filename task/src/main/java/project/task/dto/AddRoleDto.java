package project.task.dto;

import jakarta.validation.constraints.NotEmpty;

public class AddRoleDto {

    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @NotEmpty(message = "El rol no puede estar vacío")
    private String role;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
