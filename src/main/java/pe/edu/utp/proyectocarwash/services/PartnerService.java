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
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.repositories.PartnerRepository;
import pe.edu.utp.proyectocarwash.repositories.PartnerTypeCatalogRepository;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PartnerTypeCatalogRepository partnerServiceRepository;


    public List<PartnerDTO> getAllPartners() {
        List<Partner> partners = partnerRepository.findAll();
        return partners.stream()
                .map(this::mapToPartnerDto)
                .collect(Collectors.toList());

    }


    // Método para encontrar socios por ID de usuario
//    public List<Partner> findPartnersByUserId(Long userId) {
//        return partnerRepository.findPartnersByOwnerId(userId);
//    }


    // Método para encontrar socios por ID de usuario
//    public List<PartnerDTO> findPartnersByUserId(Long userId) {
//        List<Partner> partners = partnerRepository.findPartnersByOwnerId(userId);
//
//        // Convertir las imágenes a cadenas Base64
//        List<PartnerDTO> partnerDTOs = partners.stream()
//                .map(partner -> {
//                    PartnerDTO partnerDTO = new PartnerDTO();
//                    partnerDTO.setId(partner.getId());
//                    partnerDTO.setName(partner.getName());
//                    partnerDTO.setPhoneNumber(partner.getPhoneNumber());
//                    partnerDTO.setAddress(partner.getAddress());
//                    partnerDTO.setActive(partner.isActive());
//
//                    // Codificar la imagen a Base64 solo si no es nula
//                    if (partner.getImagen() != null) {
//                        String base64Image = Base64.getEncoder().encodeToString(partner.getImagen());
//                        partnerDTO.setBase64Image(base64Image);
//                    }
//
//
//                    return partnerDTO;
//                })
//                .collect(Collectors.toList());
//
//        return partnerDTOs;
//    }

    public List<PartnerDTO> findPartnersByUserId(Long userId) {
        List<Partner> partners = partnerRepository.findPartnersByOwnerId(userId);
        return partners.stream()
                .map(this::mapToPartnerDto)
                .collect(Collectors.toList());
    }





    private PartnerDTO mapToPartnerDto(Partner partner) {
        PartnerDTO partnerDto = new PartnerDTO();
        partnerDto.setId(partner.getId());
        partnerDto.setName(partner.getName());
        partnerDto.setPhoneNumber(partner.getPhoneNumber());
        partnerDto.setAddress(partner.getAddress());
        partnerDto.setDescription(partner.getDescription());
        partnerDto.setRating(partner.getRating());
        if (partner.getImagen() != null) {
            String base64Image = Base64.getEncoder().encodeToString(partner.getImagen());
            partnerDto.setBase64Image(base64Image);
        }
        partnerDto.setActive(partner.isActive());

        Set<PartnerTypeCatalogDTO> serviceDtos = partner.getServices().stream()
                .map(this::mapToPartnerTypeCatalogDto)
                .collect(Collectors.toSet());

        partnerDto.setServices(serviceDtos);
        return partnerDto;
    }

    private PartnerTypeCatalogDTO mapToPartnerTypeCatalogDto(PartnerTypeCatalog partnerTypeCatalog) {
        PartnerTypeCatalogDTO dto = new PartnerTypeCatalogDTO();
        dto.setId(partnerTypeCatalog.getId());
        dto.setServiceName(partnerTypeCatalog.getServiceName());
        dto.setPrice(partnerTypeCatalog.getPrice());
        return dto;
    }




//    public Partner savePartner(Partner partner) {
//        return partnerRepository.save(partner);
//    }

//    public void savePartner(Partner partner, User loggedInUser, MultipartFile image) throws IOException {
//        // Establecer isActive en 1 (activo) por defecto
//        partner.setActive(true);
//
//        // Establecer el owner como el usuario que ha iniciado sesión
//        partner.setOwner(loggedInUser);
//
//        // Guardar la imagen si se proporciona
//        if (!image.isEmpty()) {
//            byte[] imageBytes = image.getBytes();
//            partner.setImagen(imageBytes);
//        }
//
//        // Guardar el socio en la base de datos
//        partnerRepository.save(partner);
//    }



    public boolean savePartner(Partner partner, User loggedInUser, MultipartFile image) throws IOException {
        // Establecer isActive en 1 (activo) por defecto
        partner.setActive(true);


        // Establecer el owner como el usuario que ha iniciado sesión
        partner.setOwner(loggedInUser);

        // Convertir la imagen a bytes y guardarla en el socio
        if (!image.isEmpty()) {
            partner.setImagen(image.getBytes());
        }

        partner.setRating(5);

        // Guardar el socio en la base de datos
        partnerRepository.save(partner);
        return true;
    }



    public void deletePartnerById(Long id) {
        partnerRepository.deleteById(id);
    }




}
