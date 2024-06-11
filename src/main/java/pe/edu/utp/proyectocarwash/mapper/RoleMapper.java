package pe.edu.utp.proyectocarwash.mapper;

import pe.edu.utp.proyectocarwash.dto.RoleDTO;
import pe.edu.utp.proyectocarwash.entities.Role;

public class RoleMapper {
    public static RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public static Role mapToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
