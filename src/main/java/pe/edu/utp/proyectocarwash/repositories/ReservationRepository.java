package pe.edu.utp.proyectocarwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.Reservation;
import pe.edu.utp.proyectocarwash.entities.User;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByPartner(Partner partner);
    List<Reservation> findByPartnerOrderByIdAsc(Partner partner);

}