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
}
