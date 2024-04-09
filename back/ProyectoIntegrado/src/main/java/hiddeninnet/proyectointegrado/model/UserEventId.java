package hiddeninnet.proyectointegrado.model;

import java.io.Serializable;

public class UserEventId implements Serializable {

    private Long user;
    private Long event;

    // Getters y setters

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }
}
