package dgg.motorsphere.api.dto;

import jakarta.annotation.Nullable;
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

    @Nullable
    private Date incriptionDate;

    private Long fechaId;
}
