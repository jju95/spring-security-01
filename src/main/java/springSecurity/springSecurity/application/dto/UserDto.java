package springSecurity.springSecurity.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    @JsonProperty("user_name")
    private String userName;

    @NonNull
    @JsonProperty("nick_name")
    private String nickName;
}
