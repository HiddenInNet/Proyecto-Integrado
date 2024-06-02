package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Etiqueta;
import dgg.motorsphere.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Entity
@Getter
@Setter
@Table(name = "etiquetas_usuarios")
public class EtiquetaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etiqueta_id")
    private Etiqueta etiqueta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
