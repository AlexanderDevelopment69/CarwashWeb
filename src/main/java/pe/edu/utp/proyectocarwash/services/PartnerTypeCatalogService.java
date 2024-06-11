package pe.edu.utp.proyectocarwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.PartnerTypeCatalog;
import pe.edu.utp.proyectocarwash.repositories.PartnerTypeCatalogRepository;

@Service
public class PartnerTypeCatalogService {
    @Autowired
    private PartnerTypeCatalogRepository partnerTypeCatalogRepository;



    public void addServiceToPartner(PartnerTypeCatalog service, Partner partner) {
        service.setPartner(partner);
        partnerTypeCatalogRepository.save(service);
    }





}
