package dgg.motorsphere.api.dto.ofertante;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class OfertanteDTO implements Serializable {

    private Long id;
    private Long userId;

    private Date creationDate;
    private boolean checker;

}
