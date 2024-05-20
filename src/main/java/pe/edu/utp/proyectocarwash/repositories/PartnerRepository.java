package pe.edu.utp.proyectocarwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.User;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {


    List<Partner> findPartnersByOwnerId(Long userId);



}
