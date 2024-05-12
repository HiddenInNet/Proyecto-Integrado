package dgg.motorsphere.model.entity;

import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "fechas")
public class Fecha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaInicio;

    @Column(name = "fecha_final", nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaFinal;

    @Column(name = "precio", nullable = false, unique = false)
    private Float precio;

    @Column(name = "plazas_totales")
    private Integer plazas;

    @Column(name = "plazas_disponibles")
    private Integer plazasDisponibles;


    // RELACIONES


    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(mappedBy = "fecha", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioInscritoEvento> usuariosInscritosFecha = new HashSet<>();
}
