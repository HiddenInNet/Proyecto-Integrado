package dgg.motorsphere.model.entity;


import dgg.motorsphere.model.entity.relations.EtiquetaUsuario;
import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "nombre", unique = false, nullable = false)
    private String nombre;

    @Column(name = "apellidos", unique = false, nullable = true)
    private String apellidos;

    @Column(name = "fechaNacimiento", unique = false, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaNacimiento;

    @Column(name = "telefono", unique = false, nullable = true)
    private String telefono;

    @Column(name = "fechaCreacionPerfil", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacionPerfil;

    @Column(name = "biografia", unique = false, nullable = true)
    private String biografia;

    @Column(name = "imagenPerfil", unique = false, nullable = true)
    private String imagenPerfil;

    @Column(name = "puntuacion", unique = false, nullable = true)
    @Range(min = 0, max = 100)
    private Integer puntuacion;


    // RELACIONES

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioInscritoEvento> usuarioInscritoEventos = new HashSet<>();

    // Etiquetas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EtiquetaUsuario> etiquetasUsuario = new HashSet<>();

}
