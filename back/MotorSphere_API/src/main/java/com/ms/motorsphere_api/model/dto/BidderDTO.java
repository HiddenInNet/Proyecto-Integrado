package com.ms.motorsphere_api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class BidderDTO implements Serializable {

    private Long bidderId;
    private Long userId;

    private Date creationDate;
    private boolean checker;

}
