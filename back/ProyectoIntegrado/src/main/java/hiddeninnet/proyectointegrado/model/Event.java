package hiddeninnet.proyectointegrado.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    // Bidder
    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;

    @OneToOne(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Insignia insignia;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "event_latitude", unique = false, nullable = false)
    private Double eventLatitude;

    @Column(name = "event_longitude", unique = false, nullable = false)
    private Double eventLongitude;

    @Column(name = "image", unique = false, nullable = true)
    private byte[] image;

    @Column(name = "upload_date", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uploadDate;

    @Column(name = "due_date", unique = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserEvent> userEvents = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public void setInsignia(Insignia insignia){
        insignia.setEvent(this);
        this.insignia = insignia;
    }

    public void removeInsignia() {
        if (this.insignia != null){
            this.insignia.setEvent(null);
            this.insignia = null;
        }
    }

    // Métodos Helpers
    public void addIncriptionDate(User user, Date date) {
        UserEvent userEvent = new UserEvent(user, this, date);
        this.userEvents.add(userEvent);
        user.getUserEvents().add(userEvent);
    }

    public void removeIncriptionDate(User user) {
        UserEvent userEvent = new UserEvent(user, this);
        user.getUserEvents().remove(userEvent);
        this.userEvents.remove(userEvent);
    }
}