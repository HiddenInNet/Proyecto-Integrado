package com.ms.motorsphere_api.model.entity;

import java.io.Serializable;

public class UserEventId implements Serializable {

    private Long usuario;
    private Long event;

    // Getters y setters

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }
}
