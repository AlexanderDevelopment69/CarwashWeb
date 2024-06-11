package pe.edu.utp.proyectocarwash.mapper;

import pe.edu.utp.proyectocarwash.dto.PartnerTypeCatalogDTO;
import pe.edu.utp.proyectocarwash.entities.PartnerTypeCatalog;

public class PartnerTypeCatalogMapper {

    public static PartnerTypeCatalogDTO mapToPartnerTypeCatalogDTO(PartnerTypeCatalog partnerTypeCatalog) {
        PartnerTypeCatalogDTO partnerTypeCatalogDTO = new PartnerTypeCatalogDTO();
        partnerTypeCatalogDTO.setId(partnerTypeCatalog.getId());
        partnerTypeCatalogDTO.setServiceName(partnerTypeCatalog.getServiceName());
        partnerTypeCatalogDTO.setPrice(partnerTypeCatalog.getPrice());
        return partnerTypeCatalogDTO;
    }

    public static PartnerTypeCatalog mapToPartnerTypeCatalog(PartnerTypeCatalogDTO partnerTypeCatalogDTO) {
        PartnerTypeCatalog partnerTypeCatalog = new PartnerTypeCatalog();
        partnerTypeCatalog.setId(partnerTypeCatalogDTO.getId());
        partnerTypeCatalog.setServiceName(partnerTypeCatalogDTO.getServiceName());
        partnerTypeCatalog.setPrice(partnerTypeCatalogDTO.getPrice());
        return partnerTypeCatalog;
    }
}
