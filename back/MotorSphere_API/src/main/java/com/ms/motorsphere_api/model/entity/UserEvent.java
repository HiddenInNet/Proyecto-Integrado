package com.ms.motorsphere_api.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@ToString
@Builder
@Entity
@Table(name = "user_event")
@IdClass(UserEventId.class)
public class UserEvent implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "inscription_date", nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inscriptionDate;

    public UserEvent(Usuario usuario, Event event, Date incriptionDate) {
        super();
        this.usuario = usuario;
        this.event = event;
        this.inscriptionDate = incriptionDate;
    }

    public UserEvent(Usuario usuario, Event event) {
        super();
        this.usuario = usuario;
        this.event = event;
    }

    public UserEvent() {
        super();
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, event);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UserEvent other = (UserEvent) obj;
        return Objects.equals(usuario, other.usuario) && Objects.equals(event, other.event);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
