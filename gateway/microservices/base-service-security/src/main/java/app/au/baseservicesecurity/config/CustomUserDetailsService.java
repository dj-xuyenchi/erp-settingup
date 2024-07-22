package app.au.baseservicesecurity.config;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tải thông tin người dùng từ cơ sở dữ liệu hoặc nguồn khác
        //username - mật khẩu - danh sách quyền
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        //  roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User("user", "$2a$10$DowJxYX5wZq.aFVYKyyRxeF6MxRCkZ5OXysuNEmqBZyF7K7YzDqVe", roles);
    }
}
