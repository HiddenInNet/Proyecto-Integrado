package com.ms.motorsphere_api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class CommentDTO implements Serializable {

    private Long commentId;
    private Long userId;
    private Long publicationId;

    private Date date;
    private String information;
}
