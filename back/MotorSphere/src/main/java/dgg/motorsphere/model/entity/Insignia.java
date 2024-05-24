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
@Table(name = "insignias")
public class Insignia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "insignia")
    private Evento evento;

    @Column(nullable = false)
    private String nombre;

    private String imagen;

    @Column(nullable = false)
    private Integer valor;
}
