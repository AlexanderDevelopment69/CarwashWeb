package pe.edu.utp.proyectocarwash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.proyectocarwash.dto.PartnerDTO;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.PartnerTypeCatalog;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.services.PartnerService;
import pe.edu.utp.proyectocarwash.services.PartnerTypeCatalogService;
import pe.edu.utp.proyectocarwash.services.UserService;

import java.io.IOException;
import java.util.List;

@Controller
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerTypeCatalogService partnerTypeCatalogService;

    @GetMapping("/registroSocio")
    public String showRegistrationForm(Model model) {
        model.addAttribute("partner", new Partner());
        return "registroSocio";
    }

//    @PostMapping("/registerPartner")
//    public String registerPartner(@ModelAttribute("partner") Partner partner,
//                                  @RequestParam("image") MultipartFile image) throws IOException {
//        // Obtiene el usuario que ha iniciado sesión
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String loggedInUsername = auth.getName();
//
//        // Busca el usuario por nombre de usuario
//        User loggedInUser = userService.findByEmail(loggedInUsername);
//
//        // Guarda el socio en la base de datos a través del servicio
//        partnerService.savePartner(partner, loggedInUser,image);
//
//        return "redirect:/partner/registerPartner";
//    }


    @PostMapping("/registroSocio")
    public String registerPartner(@ModelAttribute("partner") Partner partner,
                                  @RequestParam("image") MultipartFile image,
                                  @RequestParam("serviceNames") String[] serviceNames,
                                  @RequestParam("servicePrices") double[] servicePrices,
                                  RedirectAttributes redirectAttributes) throws IOException {
        // Obtiene el usuario que ha iniciado sesión
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();

        // Busca el usuario por email
        User loggedInUser = userService.findByEmail(loggedInUsername);

        // Guardar el socio en la base de datos a través del servicio
        boolean registrationSuccess = partnerService.savePartner(partner, loggedInUser, image);

        // Si el socio se registró exitosamente
        if (registrationSuccess) {

            redirectAttributes.addFlashAttribute("registrationMessage", "¡Registrado exitosamente!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo registrar. Por favor, inténtalo de nuevo.");
        }



        // Agrega servicios al socio
        for (int i = 0; i < serviceNames.length; i++) {
            PartnerTypeCatalog service = new PartnerTypeCatalog();
            service.setServiceName(serviceNames[i]);
            service.setPrice(servicePrices[i]);
            partnerTypeCatalogService.addServiceToPartner(service, partner);
        }

        // Datos ficticios para serviceNames y servicePrices
//        String[] dummyServiceNames = {"Lavado", "Secado", "Encerado"};
//        double[] dummyServicePrices = {10.0, 15.0, 20.0};
//
//        // Agrega los datos ficticios al socio
//        for (int i = 0; i < dummyServiceNames.length; i++) {
//            PartnerTypeCatalog service = new PartnerTypeCatalog();
//            service.setServiceName(dummyServiceNames[i]);
//            service.setPrice(dummyServicePrices[i]);
//            partnerTypeCatalogService.addServiceToPartner(service, partner);
//        }


        return "redirect:/registroSocio";

    }




    @GetMapping("/miEmpresa")
    public String showUserPartners(Model model) {
        // Obtener el usuario que ha iniciado sesión
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        // Obtener el socio asociado al usuario
        List<PartnerDTO> partners = partnerService.findPartnersByUserId(loggedInUser.getId());

        // Pasar el socio a la vista
        model.addAttribute("partners", partners);

        return "my-partner";
    }






}
