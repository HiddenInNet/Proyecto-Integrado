package dgg.motorsphere.api.dto.comentario;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ComentarioDTO implements Serializable {

    private Long id;
    private Long userId;
    private Long publicationId;

    private Date date;
    private String information;
}
