package app.au.apigateway.controller;

import app.au.apigateway.entity.Customer;
import app.au.apigateway.entity.Token;
import app.au.apigateway.model.AuthenticationResponse;
import app.au.apigateway.model.RegisterModel;
import app.au.apigateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    //  private final AuthenticationServiceImpl authenticationService;

    private final JwtService jwtService = new JwtService();

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterModel registerRequest) {
        AuthenticationResponse response = register2(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public AuthenticationResponse register2(RegisterModel request) {
        Customer newUser = new Customer();
        newUser.setUserName(request.getUserName());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(newUser);
        Token token = Token.builder()
                .userId(newUser.getId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        return AuthenticationResponse.builder()
                .userDto(newUser)
                .token(token)
                .build();
    }
}