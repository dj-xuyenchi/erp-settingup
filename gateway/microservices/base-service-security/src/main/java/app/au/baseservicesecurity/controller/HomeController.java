package app.au.baseservicesecurity.controller;

import app.au.baseservicesecurity.config.JwtService;
import app.au.baseservicesecurity.entity.Token;
import app.au.baseservicesecurity.entity.User;
import app.au.baseservicesecurity.model.AuthenticationResponse;
import app.au.baseservicesecurity.model.RegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HomeController extends  BaseController{
    private final JwtService jwtService;




    @GetMapping("/khong-bao-ve/xin-chao")
    public ResponseEntity<?> method1() {
        return ResponseEntity.ok("Api này không có bảo vệ!");
    }

    @GetMapping("/bao-ve/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> method2() {
        return ResponseEntity.ok("Api này chỉ admin vào được!");
    }

    @GetMapping("/bao-ve/employee")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> method3() {
        return ResponseEntity.ok("Api này chỉ có nhân viên vào được!");
    }

    @GetMapping("/bao-ve/authorize")
    public ResponseEntity<?> method4() {
        return ResponseEntity.ok("Api này phải đăng nhập mới vào được!");
    }
}
