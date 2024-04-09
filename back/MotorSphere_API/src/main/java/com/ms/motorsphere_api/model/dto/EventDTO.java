package com.ms.motorsphere_api.model.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class EventDTO implements Serializable {

    private Long eventId;
    private Long bidderId;
    private Long insigniaId;

    private String name;
    private String description;
    private double eventLat;
    private double eventLon;
    private byte[] image;
    private Date uploadDate;
    private Date dueDate;

}
