package com.system.backend.Controller.User;

import com.system.backend.Dto.Product.PasswordUserRequest;
import com.system.backend.Dto.User.UserUpdateRequestDTO;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/user/member") // update detail and password
public class MemberController {
    @Autowired
    private UserService userService;



    //update
    @PostMapping
    public String checkUserPassword(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        String message = userService.checkUserPassword(userUpdateRequestDTO.getPassword()
                , userUpdateRequestDTO.getAccount());
        return message;
    }
    @PutMapping("/setpass")
    public String setUserPassword(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        String message = userService.setUserPassword(userUpdateRequestDTO.getPassword()
                , userUpdateRequestDTO.getAccount());
        return message;
    }
    @PutMapping("/{user_id}")
    public String updateUserDetail(@PathVariable Integer user_id,@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        String message = userService.updateUser(user_id, userUpdateRequestDTO);
        return message;
    }
    // end-update
}
