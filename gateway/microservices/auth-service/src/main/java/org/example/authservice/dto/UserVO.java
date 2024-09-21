package org.example.authservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserVO {
    private String id;
    private String email;
    private String password;
    private String role;

}
