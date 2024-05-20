package pe.edu.utp.proyectocarwash.entities;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"partner"}) // Excluie la relación bidireccional en el cálculo de hashCode y equals
@ToString(exclude = {"partner"})
public class PartnerTypeCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private double price;

    // Relación muchos a uno con Partner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

}