package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseApi<T> {
    private T data;
    private String message;
    private String code;
    private String newToken;
}
