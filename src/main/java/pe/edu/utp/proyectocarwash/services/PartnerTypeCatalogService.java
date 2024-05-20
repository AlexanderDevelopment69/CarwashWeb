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

//    public boolean addServicesToPartner(String[] serviceNames, double[] servicePrices, Partner partner) {
//        for (int i = 0; i < serviceNames.length; i++) {
//            PartnerTypeCatalog service = new PartnerTypeCatalog();
//            service.setServiceName(serviceNames[i]);
//            service.setPrice(servicePrices[i]);
//            PartnerTypeCatalog savedService = partnerTypeCatalogRepository.save(service);
//            if (savedService == null) {
//                return false; // Si falla la adición de un servicio, retorna falso
//            }
//        }
//        return true; // Retorna verdadero si se agregan todos los servicios con éxito
//    }



}
