package pe.edu.utp.proyectocarwash.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String password;
    private Set<RoleDTO> roles;
}
