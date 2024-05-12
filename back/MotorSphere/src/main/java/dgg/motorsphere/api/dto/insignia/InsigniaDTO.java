package dgg.motorsphere.api.dto.insignia;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class InsigniaDTO implements Serializable {

    private Long insigniaId;

    private String name;
    private String image;
    private Long value;

}
