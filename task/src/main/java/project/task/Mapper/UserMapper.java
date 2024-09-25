package project.task.Mapper;

import project.task.Models.Role;
import project.task.Models.User;
import project.task.dto.RoleDTO;
import project.task.dto.UserDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toDTO(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(mapRolesToDTO(user.getRoles()));
        return dto;
    }

    private static Set<RoleDTO> mapRolesToDTO(Set<Role> roles) {
        return roles.stream()
                .map(role -> {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setId(role.getId());
                    roleDTO.setName(role.getName());
                    return roleDTO;
                })
                .collect(Collectors.toSet());
    }
}
