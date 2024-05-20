package pe.edu.utp.proyectocarwash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyectocarwash.entities.Role;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
//    }


//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new CustomUserDetails(user);
//    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email); // Buscar por correo electrónico en lugar de nombre de usuario
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        if (user.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("El usuario no tiene roles asignados");
        }

        return new CustomUserDetails(user);
    }


}
