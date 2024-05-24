package dgg.motorsphere.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "localizacion")
public class Localizacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "localizacion")
    private Evento evento;

    private String latitud;

    private String longitud;

    private String municipio;

    private String provincia;

    private String pais;
}
