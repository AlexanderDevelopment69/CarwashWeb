package pe.edu.utp.proyectocarwash.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"services"}) // Exclue la relación bidireccional en el cálculo de hashCode y equals
@ToString(exclude = {"services"})
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String description;

    private int rating;

    @Lob
    private byte[] imagen;

    @Column(nullable = false)
    private boolean isActive;

    // Relación uno a uno con User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    // Relación uno a muchos con PartnerService
//    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
//    private Set<PartnerTypeCatalog> services;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PartnerTypeCatalog> services;



}
