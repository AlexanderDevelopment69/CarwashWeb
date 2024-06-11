package pe.edu.utp.proyectocarwash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.proyectocarwash.dto.PartnerDTO;
import pe.edu.utp.proyectocarwash.dto.ReservationDTO;
import pe.edu.utp.proyectocarwash.entities.*;
import pe.edu.utp.proyectocarwash.mapper.PartnerMapper;
import pe.edu.utp.proyectocarwash.services.ReservationService;
import pe.edu.utp.proyectocarwash.services.PartnerService;
import pe.edu.utp.proyectocarwash.services.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;


    @PostMapping("/reservations")
    public String scheduleAppointment(@RequestParam("partnerId") Long partnerId,
                                      @RequestParam("serviceIds") List<Long> serviceIds,
                                      @RequestParam("dateTime") String dateTimeString,
                                      @RequestParam("totalPrice") Double totalPrice,
                                      Principal principal,
                                      RedirectAttributes redirectAttributes) {
        // Obtiene el usuario que está realizando la reserva
        User user = userService.findByEmail(principal.getName());

        // Busca el socio por su ID utilizando PartnerService
        Optional<Partner> partnerOptional = partnerService.findById(partnerId);
        if (!partnerOptional.isPresent()) {
            // Manejo de error si el socio no se encuentra
            return "error";
        }
        Partner partner = partnerOptional.get();

        // Busca los servicios por sus IDs utilizando PartnerService
        List<PartnerTypeCatalog> services = serviceIds.stream()
                .map(serviceId -> partnerService.findServiceById(serviceId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        if (services.isEmpty()) {
            // Manejo de error si no se encuentran los servicios
            return "error";
        }

        // Convierte el String de fecha y hora a LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);

        // Programa la reserva
        reservationService.schedulereservation(user, partner, services, dateTime, totalPrice);


        // Agrega un atributo al modelo para indicar que la cita se ha registrado correctamente
        redirectAttributes.addFlashAttribute("success", "¡La cita se ha registrado correctamente!");
        // Redirecciona a una página de confirmación
//        return "reservation_form";
        return "redirect:/reservacion/" + partnerId;
    }


    @GetMapping("/reservacion/{partnerId}")
    public String showReservationForm(@PathVariable Long partnerId, Model model) {
        // Usa el servicio para buscar el socio por su ID
        Optional<Partner> partnerOptional = partnerService.findById(partnerId);

        // Verifica si el socio fue encontrado
        if (partnerOptional.isPresent()) {
            // Convierte el socio a un DTO utilizando el PartnerMapper
            PartnerDTO partnerDTO = PartnerMapper.mapToPartnerDTO(partnerOptional.get());
            model.addAttribute("partner", partnerDTO);
        }

        return "reservation_form";
    }

    @GetMapping("/misReservaciones")
    public String showMyReservations(Model model, Principal principal) {
        // Obtiene el usuario actual
        User user = userService.findByEmail(principal.getName());

        // Obtiene todas las reservas del usuario
        List<ReservationDTO> reservations = reservationService.findReservationsByUser(user);

        // Agrega las reservas al modelo
        model.addAttribute("reservations", reservations);

        // Devuelve la vista para mostrar las reservas
        return "my_reservations";
    }



    // Endpoint para mostrar las reservaciones del partner asociado al usuario autenticado
    @GetMapping("/empresa/atenderReservacion")
    public String showReservationsByCurrentUser(Model model) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userService.findByEmail(username);

        // Obtiene todos los partners asociados al usuario
        Set<Partner> partners = currentUser.getPartners();

        // Lista para almacenar todas las reservaciones de los partners del usuario
        List<ReservationDTO> allReservations = new ArrayList<>();

        // Obtiene las reservaciones de cada partner y agregarlas a la lista
        for (Partner partner : partners) {
            List<ReservationDTO> partnerReservations = reservationService.getReservationsByPartner(partner);
            allReservations.addAll(partnerReservations);
        }

        // Ordena todas las reservaciones por ID
        allReservations.sort(Comparator.comparing(ReservationDTO::getId));

        // Agrega las reservaciones al modelo
        model.addAttribute("reservations", allReservations);

        // Devuelve el nombre de la vista Thymeleaf
        return "empresaReservaciones";
    }


    @PostMapping("/reservations/complete")
    public String completeReservation(@RequestParam("reservationId") Long reservationId, RedirectAttributes redirectAttributes) {
        // Actualiza el estado de la reserva a completado
        reservationService.updateReservationStatus(reservationId, ReservationStatus.COMPLETADA);

        // Agrega un mensaje de éxito para mostrar al usuario
        redirectAttributes.addFlashAttribute("success", "¡El servicio ha sido marcado como completado!");

        // Redirecciona a la página de mis reservaciones o a donde desees
        return "redirect:/empresa/atenderReservacion";
    }



}