package com.system.backend.Controller;


import com.system.backend.Dto.PasswordUserRequest;
import com.system.backend.Dto.UpdateUserRequest;
import com.system.backend.Enity.User;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @DeleteMapping
    public String deleteUser(@RequestBody String account) {
        String message = userService.deleteUser(account);
        return message;
    }
    @PutMapping
    public String updateUserDetail(@RequestBody UpdateUserRequest updateUserRequest) {
        String message = userService.updateUser(updateUserRequest.getAccount(), updateUserRequest.getUserDTO());
        return message;
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> list = userService.getUser();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public String checkUserPassword(@RequestBody PasswordUserRequest checkPasswordUserRequest) {
        String message = userService.checkUserPassword(checkPasswordUserRequest.getPassword()
                , checkPasswordUserRequest.getAccount());
        return message;
    }
    @PutMapping(path = "/setpass")
    public String setUserPassword(@RequestBody PasswordUserRequest setPasswordUserRequest) {
        String message = userService.setUserPassword(setPasswordUserRequest.getPassword()
                , setPasswordUserRequest.getAccount());
        return message;
    }


}
