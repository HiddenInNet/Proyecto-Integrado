package dgg.motorsphere.model.entity;

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
@Table(name = "publicaciones")
public class Publicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = false)
    private String nombre;

    @Column(name = "fechaSubida", nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaSubida;

    @Column(name = "likes", nullable = true, unique = false)
    private Long likes;

    @Column(name = "imagen", nullable = true, unique = false)
    private byte[] imagen;

    @Column(name = "informacion", nullable = true, unique = false)
    private String informacion;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<>();

    // HELPERS
    public void addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
        comentario.setPublicacion(this);
    }

    public void removeComentario(Comentario comentario) {
        this.comentarios.remove(comentario);
        comentario.setPublicacion(null);
    }
}
