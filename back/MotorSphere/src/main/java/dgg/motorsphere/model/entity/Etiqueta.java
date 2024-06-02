package dgg.motorsphere.model.entity;

import dgg.motorsphere.model.entity.relations.EtiquetaEvento;
import dgg.motorsphere.model.entity.relations.EtiquetaUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "etiquetas")
public class Etiqueta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @OneToMany(
            mappedBy = "etiqueta"
    )
    private Set<EtiquetaEvento> eventosEtiqueta = new HashSet<>();

    @OneToMany(
            mappedBy = "etiqueta"
    )
    private Set<EtiquetaUsuario> usuariosEtiqueta = new HashSet<>();

}
