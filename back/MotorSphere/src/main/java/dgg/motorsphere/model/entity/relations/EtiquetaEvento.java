package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Etiqueta;
import dgg.motorsphere.model.entity.Evento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Entity
@Getter
@Setter
@Table(name = "etiquetas_eventos")
public class EtiquetaEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etiqueta_id")
    private Etiqueta etiqueta;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
