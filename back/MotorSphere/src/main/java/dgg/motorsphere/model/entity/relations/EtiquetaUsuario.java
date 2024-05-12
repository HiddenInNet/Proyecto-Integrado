package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Etiqueta;
import dgg.motorsphere.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "etiquetas_usuarios")
public class EtiquetaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "etiqueta_id")
    private Etiqueta etiqueta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
