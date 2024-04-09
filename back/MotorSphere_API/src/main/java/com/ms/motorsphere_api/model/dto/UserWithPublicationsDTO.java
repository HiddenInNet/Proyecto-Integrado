package com.ms.motorsphere_api.model.dto;

import com.ms.motorsphere_api.model.entity.Publication;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@ToString
@Builder
public class UserWithPublicationsDTO implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private Date birthDate;
    private String phone;
    private Date profileDate;
    private String biography;
    private byte[] profileImage;

    private List<PublicationDTO> publications;
}
