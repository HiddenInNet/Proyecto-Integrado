package dgg.motorsphere.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class IsSuscribedDTO implements Serializable {
    private Long userId;
    private Long eventId;
}
