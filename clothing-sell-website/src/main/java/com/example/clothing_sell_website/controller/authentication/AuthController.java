package com.example.clothing_sell_website.controller.authentication;


import com.example.clothing_sell_website.dto.request.AuthenticationRequest;
import com.example.clothing_sell_website.dto.request.RegisterRequest;
import com.example.clothing_sell_website.dto.respone.AuthenticationResponse;
import com.example.clothing_sell_website.dto.respone.RegisterResponse;
import com.example.clothing_sell_website.service.auth.AuthService;
import com.example.clothing_sell_website.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateLogin(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerAccount(
            @RequestBody RegisterRequest request
    ){
        RegisterResponse response = authService.registerNewAccount(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/info")
    public ResponseEntity<String> getUserInfo(
            @RequestHeader("Authorization") String authorizationHeader
    ){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtService.isTokenValid(token)) {
                String username = jwtService.extractUsername(token);
                return ResponseEntity.ok(username);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không tồn tại");
    }
}
