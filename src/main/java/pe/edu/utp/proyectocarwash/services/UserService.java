package pe.edu.utp.proyectocarwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyectocarwash.entities.Role;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.repositories.RoleRepository;
import pe.edu.utp.proyectocarwash.repositories.UserRepository;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Codificar la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Obtener el rol predeterminado desde la base de datos (por ejemplo, ROLE_USER)
        Role defaultRole = roleRepository.findByName("ROLE_USER");
        if (defaultRole == null) {
            throw new RuntimeException("No se encontró el rol predeterminado");
        }

        // Asignar el rol al usuario
        user.setRoles(Collections.singleton(defaultRole));

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public User registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Role userRole = roleRepository.findByName("ROLE_USER");
//        user.setRoles(Collections.singleton(userRole));
//        return userRepository.save(user);
//    }
}





