package pe.edu.utp.proyectocarwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyectocarwash.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}