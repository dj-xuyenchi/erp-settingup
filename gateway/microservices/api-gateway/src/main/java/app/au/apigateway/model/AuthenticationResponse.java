package app.au.apigateway.model;


import app.au.apigateway.entity.Customer;
import app.au.apigateway.entity.Token;
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
    private Customer userDto;
}
