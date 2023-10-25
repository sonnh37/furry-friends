package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserAuthRequest;
import com.system.backend.Service.UserService;
import com.system.backend.Dto.User.LoginMessage;
import com.system.backend.util.JwtUtil;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT)
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping(Link.LOGIN)
    public ResponseEntity<?> generateToken(@RequestBody UserAuthRequest authRequest) throws Exception {
        LoginMessage loginResponse = userService.loginUser(authRequest);
        if(loginResponse.getStatus()){

            return ResponseEntity.ok(userService.createToken(authRequest));  // true-token
        }
        return ResponseEntity.ok(loginResponse.getMessage());// false - message
    }
}
