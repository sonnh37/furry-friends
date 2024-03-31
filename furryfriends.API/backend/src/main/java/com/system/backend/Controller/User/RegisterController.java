package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserRegisterRequest;
import com.system.backend.Service.UserService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT)
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping(Link.REGISTER)
    public String saveUser(@RequestBody UserRegisterRequest user)
    {
        return userService.addUser(user);
    }
}
