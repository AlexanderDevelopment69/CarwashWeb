package pe.edu.utp.proyectocarwash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.utp.proyectocarwash.dto.PartnerDTO;
import pe.edu.utp.proyectocarwash.services.PartnerService;
import pe.edu.utp.proyectocarwash.services.UserService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PartnerService partnerService;





    @GetMapping("/home")
    public String home(Model model) {
        // Obtener la lista de partners utilizando el servicio
        List<PartnerDTO> partners = partnerService.getAllPartners();


//        // Imprime los datos por consola
//        System.out.println("Lista de partners:");
//        for (PartnerDTO partner : partners) {
////            System.out.println(partner.toString());
//            System.out.println(partner.getServices().toString());
//        }

        // Agrega la lista de partners al modelo para que esté disponible en la vista
        model.addAttribute("partners", partners);

        // Devuelve el nombre de la vista que deseas mostrar
        return "home";
    }




    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/home"; // Redirige a /home
    }

}