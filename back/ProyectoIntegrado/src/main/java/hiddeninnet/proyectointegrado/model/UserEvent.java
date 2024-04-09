package hiddeninnet.proyectointegrado.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_event")
@IdClass(UserEventId.class)
public class UserEvent implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    @Column(name = "inscription_date", nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inscriptionDate;

    public UserEvent(User user, Event event, Date incriptionDate) {
        super();
        this.user = user;
        this.event = event;
        this.inscriptionDate = incriptionDate;
    }

    public UserEvent(User user, Event event) {
        super();
        this.user = user;
        this.event = event;
    }

    public UserEvent() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UserEvent other = (UserEvent) obj;
        return Objects.equals(user, other.user) && Objects.equals(event, other.event);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
