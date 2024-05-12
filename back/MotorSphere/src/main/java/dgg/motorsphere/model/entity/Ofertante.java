package dgg.motorsphere.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ofertantes")
public class Ofertante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy = "ofertante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evento> eventos = new HashSet<>();

    @Column(name = "fechaCreacion", unique = false, nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion;

    @Column(name = "checker", unique = false, nullable = true)
    private boolean checker;

    // HELPERS
    public void addEvento(Evento evento) {
        this.eventos.add(evento);
        evento.setOfertante(this);
    }

    public void removeEvento(Evento evento) {
        this.eventos.remove(evento);
        evento.setOfertante(null);
    }
}
