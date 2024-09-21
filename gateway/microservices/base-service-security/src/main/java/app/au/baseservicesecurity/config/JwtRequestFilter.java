package app.au.baseservicesecurity.config;

import api.ResponseApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtService jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        // nếu không phải URI authorize thì cho đi tiếp luôn
        if (!uri.contains("/bao-ve/")) {
            chain.doFilter(request, response);
            return;
        }
        final String requestTokenHeader = request.getHeader("Authorization");
        // Lấy thông tin đăng nhập từ token
        if (requestTokenHeader != null && !requestTokenHeader.startsWith("Bearer ")) {
            log.error("API call không có token đi kèm: " + uri);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: No token provided");
            return;
        }
        String jwtToken = requestTokenHeader.substring(7);
        Claims claims;

        try {
            claims = jwtUtil.getClaimsFromToken(jwtToken);
        } catch (ExpiredJwtException e) {
            log.error("Token het han: {}", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            ResponseApi<String> responseApi = new ResponseApi<>();
            responseApi.setMessage("Token het han!");
            responseApi.setData(null);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK); // Trả về mã 200
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseApi));
            response.getWriter().flush();
            return;
        } catch (SignatureException e) {
            log.error("Chu ky khong hop le: {}", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            ResponseApi<String> responseApi = new ResponseApi<>();
            responseApi.setMessage("Chu ky khong hop le!");
            responseApi.setData(null);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK); // Trả về mã 200
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseApi));
            response.getWriter().flush();
            return;
        }
        String username = claims.getSubject();
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // If token is valid configure Spring Security to manually set authentication
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}
