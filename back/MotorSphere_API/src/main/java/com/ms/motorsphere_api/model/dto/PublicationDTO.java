package com.ms.motorsphere_api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class PublicationDTO implements Serializable {

    private Long publicationId;
    private Long userId;

    private String name;
    private String information;
    private Date uploadDate;
    private Long likes;
    private byte[] image;
}
