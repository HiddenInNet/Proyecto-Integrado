package dgg.motorsphere.model.entity.relations;

import dgg.motorsphere.model.entity.Evento;
import dgg.motorsphere.model.entity.Vehiculo;
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
@Table(name = "vehiculos_eventos")
public class VehiculoEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;


    // RELACIONES


    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
