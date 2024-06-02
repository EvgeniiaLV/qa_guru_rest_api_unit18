package models.lombok;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseModel {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    @JsonProperty("created_date")
    private String createdDate;
    private String isActive;
}
