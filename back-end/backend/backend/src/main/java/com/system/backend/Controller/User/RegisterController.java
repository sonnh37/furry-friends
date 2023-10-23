package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserRegisterRequest;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/register")
    public String saveUser(@RequestBody UserRegisterRequest user)
    {
        return userService.addUser(user);
    }
}
