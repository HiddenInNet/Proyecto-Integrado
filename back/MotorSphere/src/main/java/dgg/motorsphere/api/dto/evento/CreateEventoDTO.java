package dgg.motorsphere.api.dto.evento;

import dgg.motorsphere.api.dto.FechasEventoDTO;
import dgg.motorsphere.api.dto.insignia.InsigniaDTO;
import dgg.motorsphere.api.dto.localizacion.LocalizacionDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
public class CreateEventoDTO implements Serializable {
    private Long eventId;
    private Long bidderId;

    private String name;
    private String description;
    private String image;
    private Date announcementDate;
    private List<FechasEventoDTO> dates;
    private LocalizacionDTO localization;
    private InsigniaDTO insignia;
    private Integer score;
    private Integer exigency;

}
