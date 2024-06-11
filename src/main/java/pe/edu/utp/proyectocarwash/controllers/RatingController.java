package pe.edu.utp.proyectocarwash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.proyectocarwash.entities.Rating;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.services.RatingService;
import pe.edu.utp.proyectocarwash.services.PartnerService;
import pe.edu.utp.proyectocarwash.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;



    @GetMapping
    public String getAllRatings(Model model) {
        List<Rating> ratings = ratingService.getAllRatings();
        model.addAttribute("ratings", ratings);
        return "ratings/list";
    }

    @PostMapping("/add")
    public String addRating(@RequestParam("partnerId") Long partnerId,
                            @RequestParam("score") int score,
                            @RequestParam("comment") String comment,
                            Principal principal,
                            RedirectAttributes redirectAttributes) {
        // Obtiene el usuario autenticado
        String userEmail = principal.getName();
        User user = userService.findByEmail(userEmail);

        // Busca el socio por su ID
        Optional<Partner> partnerOptional = partnerService.findById(partnerId);
        if (!partnerOptional.isPresent()) {
            // Maneja de error si el socio no se encuentra
            redirectAttributes.addFlashAttribute("error", "Socio no encontrado");
            return "redirect:/some-error-page";
        }
        Partner partner = partnerOptional.get();

        // Guarda la calificacion
        ratingService.saveRating(user, partner, score, comment);

        // Redirecciona con mensaje de éxito y con la indicación de que el modal debe estar abierto
        redirectAttributes.addFlashAttribute("success", "Calificación guardada correctamente");
//        redirectAttributes.addFlashAttribute("showModal", true);
        return "redirect:/misReservaciones?showModal=true";


    }



}
