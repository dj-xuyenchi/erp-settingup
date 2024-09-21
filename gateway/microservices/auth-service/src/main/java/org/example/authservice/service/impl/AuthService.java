package org.example.authservice.service.impl;

import api.ResponseApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.example.authservice.dto.AuthRequest;
import org.example.authservice.dto.AuthResponse;
import org.example.authservice.dto.UserVO;
import org.example.authservice.service.IAuthService;
import org.example.authservice.utility.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {
    private final JwtUtil _jwt;

    @Override
    public ResponseApi<AuthResponse> register(AuthRequest authRequest) {
        ResponseApi<AuthResponse> res = new ResponseApi<>();
        UserVO fake = getUserById("1");
        String accessToken = _jwt.generate(fake, "ACCESS");
        String refreshToken = _jwt.generate(fake, "REFRESH");
        AuthResponse token = new AuthResponse();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        res.setData(token);
        res.setMessage("OK");
        res.setCode("200");
        return res;

    }

    @Override
    public ResponseApi<AuthResponse> refreshToken(String refreshToken) {
        ResponseApi<AuthResponse> res = new ResponseApi<>();
        try {
            Claims claims = _jwt.getClaimsFromToken(refreshToken);
            UserVO fake = getUserById("1");
            String accessToken = _jwt.generate(fake, "ACCESS");
            String newRefreshToken = _jwt.generate(fake, "REFRESH");
            AuthResponse token = new AuthResponse();
            token.setAccessToken(accessToken);
            token.setRefreshToken(newRefreshToken);
            res.setData(token);
            res.setMessage("OK");
            res.setCode("200");
            return res;
        } catch (ExpiredJwtException e) {
            log.error("Refresh token het han: {}", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            res.setMessage("Refresh token het han!");
            res.setData(null);
            res.setCode("");
            return res;
        } catch (SignatureException e) {
            log.error("Refresh token chu ky khong hop le: {}", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            res.setMessage("Refresh token chu ky khong hop le!");
            res.setData(null);
            res.setCode("");
            return res;
        }
    }

    @Override
    public ResponseApi<AuthResponse> login(AuthRequest authRequest) {
        return null;
    }

    private UserVO getUserById(String id) {
        UserVO fake = new UserVO();
        fake.setEmail("aa");
        fake.setPassword("sss");
        fake.setId("1");
        fake.setRole("ADMIN");
        return fake;
    }
}
