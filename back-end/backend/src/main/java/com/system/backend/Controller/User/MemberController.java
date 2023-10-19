package com.system.backend.Controller.User;

import com.system.backend.Dto.PasswordUserRequest;
import com.system.backend.Dto.UpdateUserRequest;
import com.system.backend.Dto.UserDTO;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/user/member") // update detail and password
public class MemberController {
    @Autowired
    private UserService userService;
    @PostMapping
    public String checkUserPassword(@RequestBody PasswordUserRequest checkPasswordUserRequest) {
        String message = userService.checkUserPassword(checkPasswordUserRequest.getPassword()
                , checkPasswordUserRequest.getAccount());
        return message;
    }
    @PutMapping("/setpass")
    public String setUserPassword(@RequestBody PasswordUserRequest setPasswordUserRequest) {
        String message = userService.setUserPassword(setPasswordUserRequest.getPassword()
                , setPasswordUserRequest.getAccount());
        return message;
    }
    @PutMapping("/{user_id}")
    public String updateUserDetail(@PathVariable Integer user_id,@RequestBody UserDTO userDTO) {
        String message = userService.updateUser(user_id, userDTO);
        return message;
    }
}
