package pe.edu.utp.proyectocarwash.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.utp.proyectocarwash.dto.PartnerDTO;
import pe.edu.utp.proyectocarwash.dto.PartnerTypeCatalogDTO;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.PartnerTypeCatalog;
import pe.edu.utp.proyectocarwash.entities.Rating;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.mapper.PartnerMapper;
import pe.edu.utp.proyectocarwash.repositories.PartnerRepository;
import pe.edu.utp.proyectocarwash.repositories.PartnerTypeCatalogRepository;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PartnerTypeCatalogRepository partnerTypeCatalogRepository;



    public List<PartnerDTO> getAllPartners() {
        List<Partner> partners = partnerRepository.findAll();
        return partners.stream()
                .map(PartnerMapper::mapToPartnerDTO)
                .collect(Collectors.toList());
    }




    public List<PartnerDTO> findPartnersByUserId(Long userId) {
        List<Partner> partners = partnerRepository.findPartnersByOwnerId(userId);
        return partners.stream()
                .map(PartnerMapper::mapToPartnerDTO)
                .collect(Collectors.toList());
    }



    private PartnerTypeCatalogDTO mapToPartnerTypeCatalogDto(PartnerTypeCatalog partnerTypeCatalog) {
        PartnerTypeCatalogDTO dto = new PartnerTypeCatalogDTO();
        dto.setId(partnerTypeCatalog.getId());
        dto.setServiceName(partnerTypeCatalog.getServiceName());
        dto.setPrice(partnerTypeCatalog.getPrice());
        return dto;
    }



    public boolean savePartner(Partner partner, User loggedInUser, MultipartFile image) throws IOException {
        // Establece isActive en 1 (activo) por defecto
        partner.setActive(true);
        // Establece el owner como el usuario que ha iniciado sesión
        partner.setOwner(loggedInUser);
        // Convierte la imagen a bytes y guardarla en el socio
        if (!image.isEmpty()) {
            partner.setImagen(image.getBytes());
        }
        // Guarda el socio en la base de datos
        partnerRepository.save(partner);
        return true;
    }





    public void deletePartnerById(Long id) {
        partnerRepository.deleteById(id);
    }


    public double calculateAverageRating(Set<Rating> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return 0.0; // Si no hay calificaciones, la calificación promedio es cero
        }

        int totalScores = 0;
        for (Rating rating : ratings) {
            totalScores += rating.getScore();
        }

        return (double) totalScores / ratings.size(); // Calificación promedio = suma de calificaciones / número de calificaciones
    }




    public Optional<Partner> findById(Long id) {
        return partnerRepository.findById(id);
    }

    public Optional<PartnerTypeCatalog> findServiceById(Long id) {
        return partnerTypeCatalogRepository.findById(id);
    }

}
