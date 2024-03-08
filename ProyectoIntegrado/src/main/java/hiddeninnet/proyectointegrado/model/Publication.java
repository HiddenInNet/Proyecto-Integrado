package hiddeninnet.proyectointegrado.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publications")
public class Publication implements Serializable {

    // Class User
    @ManyToOne
    @JoinColumn(name="id_user")
    User user;
    ////////////////////////////////////////////////////

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "date", nullable = false, unique = false)
    private Date date;

    @Column(name = "likes", nullable = true, unique = false)
    private Long likes;

    @Column(name = "image", nullable = false, unique = false)
    private byte[] image;

    @Column(name = "information", nullable = true, unique = false)
    private String information;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

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
