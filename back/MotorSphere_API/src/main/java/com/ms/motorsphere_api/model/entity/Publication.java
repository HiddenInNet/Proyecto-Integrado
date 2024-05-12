package com.ms.motorsphere_api.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "publications")
public class Publication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "upload_date", nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "likes", nullable = true, unique = false)
    private Long likes;

    @Column(name = "image", nullable = true, unique = false)
    private byte[] image;

    @Column(name = "information", nullable = true, unique = false)
    private String information;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    // HELPERS
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPublication(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPublication(null);
    }
}
