package com.system.backend.Controller;

import com.system.backend.Dto.UserDTO;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1")
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/register")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        System.out.println(userDTO.getPassword() +", " +userDTO.getAccount());
        return userService.addUser(userDTO);
    }
}
