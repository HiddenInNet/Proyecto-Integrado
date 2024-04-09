package com.ms.motorsphere_api.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class UserDTO implements Serializable {

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
}
