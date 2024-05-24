package dgg.motorsphere.api.dto.login;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Optional;

@Data
@ToString
@Builder
public class LoginResponseDTO implements Serializable {
    private String jwt;
    private Long userId;
}
