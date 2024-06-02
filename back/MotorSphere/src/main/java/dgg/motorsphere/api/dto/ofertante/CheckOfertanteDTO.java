package dgg.motorsphere.api.dto.ofertante;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CheckOfertanteDTO implements Serializable {

    private Long bidderId;
    private boolean checker;
}
