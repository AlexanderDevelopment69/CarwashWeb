package pe.edu.utp.proyectocarwash.mapper;

import pe.edu.utp.proyectocarwash.dto.PartnerDTO;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.Rating;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class PartnerMapper {

    public static PartnerDTO mapToPartnerDTO(Partner partner) {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setId(partner.getId());
        partnerDTO.setName(partner.getName());
        partnerDTO.setPhoneNumber(partner.getPhoneNumber());
        partnerDTO.setAddress(partner.getAddress());
        partnerDTO.setDescription(partner.getDescription());
        partnerDTO.setRating(calculateAverageRating(partner));
        partnerDTO.setActive(partner.isActive());
        // Convierte la imagen a Base64
        if (partner.getImagen() != null) {
            String base64Image = Base64.getEncoder().encodeToString(partner.getImagen());
            partnerDTO.setBase64Image(base64Image);
        }

        partnerDTO.setServices(partner.getServices().stream()
                .map(PartnerTypeCatalogMapper::mapToPartnerTypeCatalogDTO)
                .collect(Collectors.toList()));
        partnerDTO.setRatings(partner.getRatings().stream()
                .map(RatingMapper::mapToRatingDTO)
                .collect(Collectors.toList()));
        return partnerDTO;
    }

//    private static double calculateAverageRating(Partner partner) {
//        List<Rating> ratings = partner.getRatings();
//        if (ratings == null || ratings.isEmpty()) {
//            return 0.0;
//        }
//
//        int totalScore = 0;
//        for (Rating rating : ratings) {
//            totalScore += rating.getScore();
//        }
//
//        return (double) totalScore / ratings.size();
//    }


    public static double calculateAverageRating(Partner partner) {
        List<Rating> ratings = partner.getRatings();
        return ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
    }

    public static Partner mapToPartner(PartnerDTO partnerDTO) {
        Partner partner = new Partner();
        partner.setId(partnerDTO.getId());
        partner.setName(partnerDTO.getName());
        partner.setPhoneNumber(partnerDTO.getPhoneNumber());
        partner.setAddress(partnerDTO.getAddress());
        partner.setDescription(partnerDTO.getDescription());
        partner.setActive(partnerDTO.isActive());
        // Convierte la imagen de Base64 a byte[]
        if (partnerDTO.getImagen() != null) {
            byte[] imagenBytes = Base64.getDecoder().decode(partnerDTO.getImagen());
            partner.setImagen(imagenBytes);
        }

        // Mapea la lista de servicios si está presente en el DTO
        if (partnerDTO.getServices() != null) {
            partner.setServices(partnerDTO.getServices().stream()
                    .map(PartnerTypeCatalogMapper::mapToPartnerTypeCatalog)
                    .collect(Collectors.toSet()));
        }
        // Mapea la lista de calificaciones si está presente en el DTO
        if (partnerDTO.getRatings() != null) {
            partner.setRatings(partnerDTO.getRatings().stream()
                    .map(RatingMapper::mapToRating)
                    .collect(Collectors.toList()));
        }
        return partner;
    }
}
