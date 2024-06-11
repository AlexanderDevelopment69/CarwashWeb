package pe.edu.utp.proyectocarwash.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToMany
    @JoinTable(
            name = "reservation_services",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<PartnerTypeCatalog> services;

    private LocalDateTime dateTime;

    private Double totalPrice;


    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.PENDIENTE;
}
