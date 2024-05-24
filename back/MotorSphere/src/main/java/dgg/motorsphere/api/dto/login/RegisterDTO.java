package dgg.motorsphere.api.dto.login;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class RegisterDTO implements Serializable {

    private String username;
    private String password;

    private String biography;
    private String birthDate;
    private String email;
    private String name;
    private String lastName;
    private String phone;
    private String profileImage;
}
