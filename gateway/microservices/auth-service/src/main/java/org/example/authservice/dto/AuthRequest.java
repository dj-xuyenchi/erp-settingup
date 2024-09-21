package org.example.authservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
