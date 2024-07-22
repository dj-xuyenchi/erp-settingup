package app.au.baseservicesecurity.model;


import app.au.baseservicesecurity.entity.Token;
import app.au.baseservicesecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Token token;
    private User userDto;
}
