package app.au.apigateway.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    public Integer id;

    public String token;

    public String tokenType = "Bearer ";

    public boolean revoked;

    public boolean expired;

    public Long userId;
}