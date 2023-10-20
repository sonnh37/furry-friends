package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserRegisterRequestDTO;
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
    public String saveUser(@RequestBody UserRegisterRequestDTO user)
    {
        System.out.println(user.getPassword() +", " + user.getAccount());
        return userService.addUser(user);
    }
}
