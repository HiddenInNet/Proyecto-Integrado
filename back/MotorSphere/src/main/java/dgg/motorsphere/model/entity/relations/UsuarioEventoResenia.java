package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Evento;
import dgg.motorsphere.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "usuario_evento_resenia")
public class UsuarioEventoResenia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @Column(nullable = false)
    private Integer puntuacion;

    @Column(nullable = false)
    private Date fecha_resenia;

    private String informacion;
}
