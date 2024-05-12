package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Evento;
import dgg.motorsphere.model.entity.Fecha;
import dgg.motorsphere.model.entity.Usuario;
import dgg.motorsphere.model.entity.Vehiculo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "usuarios_inscritos_eventos")
public class UsuarioInscritoEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inscripcion", nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaInscripcion;


    // RELACIONES


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "fecha_id")
    private Fecha fecha;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

}
