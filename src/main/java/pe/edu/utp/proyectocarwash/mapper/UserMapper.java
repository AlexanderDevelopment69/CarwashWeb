package pe.edu.utp.proyectocarwash.mapper;

import pe.edu.utp.proyectocarwash.dto.UserDTO;
import pe.edu.utp.proyectocarwash.entities.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setApellido(user.getApellido());
        userDTO.setDni(user.getDni());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles().stream()
                .map(RoleMapper::mapToRoleDTO) // Convierte cada Role a RoleDTO
                .collect(Collectors.toSet()));
        return userDTO;
    }

    public static User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setNombre(userDTO.getNombre());
        user.setApellido(userDTO.getApellido());
        user.setDni(userDTO.getDni());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles().stream()
                .map(RoleMapper::mapToRole) // Convierte cada RoleDTO a Role
                .collect(Collectors.toSet()));
        return user;
    }
}
