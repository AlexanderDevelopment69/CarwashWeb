package pe.edu.utp.proyectocarwash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registro_usuario";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            // Si el registro fue exitoso, enviar un mensaje de éxito al usuario
            redirectAttributes.addFlashAttribute("successMessage", "¡Bienvenido, has sido registrado exitosamente!");
            // Redirige al usuario nuevamente al formulario de registro
            return "redirect:/register";
        } catch (Exception e) {
            // Si hubo un error en el registro, enviar un mensaje de error
            redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar usuario. Por favor, inténtalo de nuevo.");
            // Redirige al usuario nuevamente al formulario de registro
            return "redirect:/register";
        }
    }


    // Muestra el formulario de inicio de sesión
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Devuelve la vista login.html
    }







}
