package dgg.motorsphere.api.dto.usuario;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class UsuarioInsertDTO implements Serializable {
    private Long id;
    private Long userId;
    private String email;
    private String name;
    private String lastName;
    private Date birthDate;
    private String phone;
    private Date profileDate;
    private String biography;
    private String profileImage;
}
