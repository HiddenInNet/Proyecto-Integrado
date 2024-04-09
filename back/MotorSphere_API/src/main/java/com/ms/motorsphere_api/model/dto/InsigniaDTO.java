package com.ms.motorsphere_api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class InsigniaDTO implements Serializable {

    private Long insigniaId;

    private String name;
    private byte[] image;
    private Long value;

}
