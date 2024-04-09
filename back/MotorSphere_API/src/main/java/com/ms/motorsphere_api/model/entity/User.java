package com.ms.motorsphere_api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("user")
    @OneToOne(mappedBy = "user")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private Set<Publication> publications = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private Set<UserEvent> userEvents = new HashSet<>();

    // HELPERS
    public void addPublication(Publication publication) {
        this.publications.add(publication);
        publication.setUser(this);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
        publication.setUser(null);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
