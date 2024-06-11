package pe.edu.utp.proyectocarwash.dto;

import lombok.Data;
import pe.edu.utp.proyectocarwash.entities.PartnerTypeCatalog;
import pe.edu.utp.proyectocarwash.entities.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationDTO {
    private Long id;
    private UserDTO user;
    private PartnerDTO partner;
    private List<PartnerTypeCatalog> services;
    private LocalDateTime dateTime;
    private Double totalPrice;
    private ReservationStatus status;

}