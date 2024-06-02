package dgg.motorsphere.api.dto.ofertante;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class OfertanteUsuarioDTO implements Serializable {

    private Long bidderId;
    private String email;
    private String username;
    private String name;
    private String lastName;
    private Date birthDate;
    private String phone;
    private Date profileDate;
    private String biography;
    private String profileImage;

    private Long userId;
    private Date creationDate;
    private boolean checker;
}
