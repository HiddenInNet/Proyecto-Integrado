package hiddeninnet.proyectointegrado.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    // Class Publication
    @ManyToOne
    @JoinColumn(name = "id_publication")
    Publication publication;
    /////////////////////////////////////////

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "image", unique = false, nullable = false)
    private byte[] image;

    @Column(name = "date", unique = false, nullable = false)
    private Date date;

    @Column(name = "information", unique = false, nullable = false)
    private String information;

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
