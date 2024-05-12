package dgg.motorsphere.api.dto.localizacion;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class LocalizacionDTO implements Serializable {

    private Long id;
    private String latitude;
    private String longitude;
    private String municipality;
    private String province;
    private String country;
}
