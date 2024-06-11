

package pe.edu.utp.proyectocarwash.dto;

import lombok.Data;

import java.util.List;

@Data
public class PartnerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String description;
    private double rating;
    private String imagen;
    private String base64Image;
    private boolean isActive;
    private List<PartnerTypeCatalogDTO> services; // Lista de servicios
    private List<RatingDTO> ratings; // Lista de calificaciones
    private double averageRating;

}

