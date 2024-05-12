package dgg.motorsphere.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
public class FechasEventoDTO implements Serializable {
    private Long id;
    private Date startDate;
    private Date finalDate;
    private Float price;
    private Integer places;
    private Integer placesAvailable;

}
