package pe.edu.utp.proyectocarwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByPartner(Partner partner);
}
