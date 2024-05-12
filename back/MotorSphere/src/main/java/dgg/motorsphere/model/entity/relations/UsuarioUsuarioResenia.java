package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "resenias_usuarios_a_usuario")
public class UsuarioUsuarioResenia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_emisor_id")
    private Usuario usuarioEmisor;

    @ManyToOne
    @JoinColumn(name = "usuario_receptor_id")
    private Usuario usuarioReceptor;

    private Integer valoracion;
}
