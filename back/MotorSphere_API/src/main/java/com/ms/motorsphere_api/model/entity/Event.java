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
@Table(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Insignia insignia;

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
    public void addIncriptionDate(Usuario usuario, Date date) {
        UserEvent userEvent = new UserEvent(usuario, this, date);
        this.userEvents.add(userEvent);
        usuario.getUserEvents().add(userEvent);
    }

    public void removeIncriptionDate(Usuario usuario) {
        UserEvent userEvent = new UserEvent(usuario, this);
        usuario.getUserEvents().remove(userEvent);
        this.userEvents.remove(userEvent);
    }
}
