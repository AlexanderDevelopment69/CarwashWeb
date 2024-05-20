package pe.edu.utp.proyectocarwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyectocarwash.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
