package dgg.motorsphere.model.entity;

import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import dgg.motorsphere.model.entity.relations.VehiculoEvento;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String imagen;


    // RELACIONES


    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioInscritoEvento> usuariosInscritosVehiculo = new HashSet<>();

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VehiculoEvento> eventoVehiculos = new HashSet<>();
}
