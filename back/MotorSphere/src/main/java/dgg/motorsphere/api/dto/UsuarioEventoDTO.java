package dgg.motorsphere.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class UsuarioEventoDTO implements Serializable {

    private Long userId;
    private Long eventId;

    private Date incriptionDate;
}
