package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserAuthRequest;
import com.system.backend.Service.UserService;
import com.system.backend.Dto.User.LoginMessage;
import com.system.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody UserAuthRequest authRequest) throws Exception {
        LoginMessage loginResponse = userService.loginUser(authRequest);
        if(loginResponse.getStatus()){

            return ResponseEntity.ok(userService.createToken(authRequest));  // true-token
        }
        return ResponseEntity.ok(loginResponse.getMessage());// false - message
    }
}
