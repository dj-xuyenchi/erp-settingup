package app.au.apigateway.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JwtAuthenticationToken(UserDetails principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    @Override
    public Object getCredentials() {
        return null; // JWT không có mật khẩu, vì vậy chúng ta không cần lưu trữ thông tin này
    }

    @Override
    public Object getPrincipal() {
        return super.getPrincipal();
    }
}
