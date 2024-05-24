package dgg.motorsphere.model.entity;

import dgg.motorsphere.model.entity.relations.EtiquetaEvento;
import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;

@Data
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

    @Column(name = "puntuacion", unique = false, nullable = false)
    @Range(min = 0, max = 100)
    private Integer puntuacion;

    @Column(name = "exigencia", unique = false, nullable = false)
    @Range(min = 0, max = 100)
    private Integer exigencia;


    // Relaciones


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insignia_id", referencedColumnName = "id")
    private Insignia insignia;

    @ManyToOne
    @JoinColumn(name = "ofertante_id")
    private Ofertante ofertante;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioInscritoEvento> usuariosInscritosEvento = new HashSet<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fecha> fechas = new HashSet<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EtiquetaEvento> etiquetasEvento = new HashSet<>();

    /*
    public void setInsignia(Insignia insignia){
        insignia.setEvento(this);
        this.insignia = insignia;
    }

    public void removeInsignia() {
        if (this.insignia != null){
            this.insignia.setEvento(null);
            this.insignia = null;
        }
    }

    // Métodos Helpers
    public void addFechaInscripcion(Usuario usuario, Date date) {
        UsuarioInscritoEvento usuarioInscritoEvento = UsuarioInscritoEvento.builder()
                .usuario(usuario)
                .evento(this)
                .fechaInscripcion(date)
                .build();
        this.usuariosInscritosEvento.add(usuarioInscritoEvento);
        usuario.getUsuarioInscritoEventos().add(usuarioInscritoEvento);
    }

    public void removeFechaInscripcion(Usuario usuario) {
        UsuarioInscritoEvento usuarioInscritoEvento = new UsuarioInscritoEvento(usuario, this);
        usuario.getUsuarioInscritoEventos().remove(usuarioInscritoEvento);
        this.usuariosInscritosEvento.remove(usuarioInscritoEvento);
    }
    */
}
