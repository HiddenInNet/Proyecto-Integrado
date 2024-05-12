package com.ms.motorsphere_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;

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
    @JoinColumn(name = "login_id")
    private Login login;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "last_name", unique = false, nullable = true)
    private String lastName;

    @Column(name = "birth_date", unique = false, nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "phone", unique = false, nullable = true)
    private String phone;

    @Column(name = "profile_create_date", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date profileDate;

    @Column(name = "biography", unique = false, nullable = true)
    private String biography;

    @Column(name = "profile_image", unique = false, nullable = true)
    private byte[] profileImage;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Publication> publications = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserEvent> userEvents = new HashSet<>();

    // HELPERS
    public void addPublication(Publication publication) {
        this.publications.add(publication);
        publication.setUsuario(this);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
        publication.setUsuario(null);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setUsuario(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setUsuario(null);
    }

}
