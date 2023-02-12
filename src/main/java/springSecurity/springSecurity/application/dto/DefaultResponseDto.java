package springSecurity.springSecurity.application.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponseDto<T> {

    private HttpStatus resultCode;
    private String message;
    private T data;

}
