package pe.edu.utp.proyectocarwash.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PartnerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String description;
    private int rating;
    private String imagen;
    private String base64Image;
    private boolean isActive;
    private Set<PartnerTypeCatalogDTO> services;

}
