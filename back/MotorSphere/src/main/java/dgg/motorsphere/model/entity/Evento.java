package dgg.motorsphere.model.entity;

import dgg.motorsphere.model.entity.relations.EtiquetaEvento;
import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = false, nullable = false)
    private String nombre;

    @Column(name = "descripcion", unique = false, nullable = false)
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "fecha_anuncio_evento", unique = false, nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAnuncioEvento;

    @Column(name = "puntuacion")
    @Range(min = 0, max = 100)
    private Integer puntuacion;

    @Column(name = "exigencia", unique = false, nullable = false)
    @Range(min = 0, max = 100)
    private Integer exigencia;

    @OneToOne
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @OneToOne
    @JoinColumn(name = "insignia_id", referencedColumnName = "id")
    private Insignia insignia;

    @ManyToOne
    @JoinColumn(name = "ofertante_id")
    private Ofertante ofertante;

    @OneToMany(mappedBy = "evento")
    private Set<UsuarioInscritoEvento> usuariosInscritosEvento = new HashSet<>();

    @OneToMany(mappedBy = "evento", orphanRemoval = true)
    private Set<Fecha> fechas = new HashSet<>();

    @OneToMany(mappedBy = "evento", orphanRemoval = true)
    private Set<EtiquetaEvento> etiquetasEvento = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

