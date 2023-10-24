package com.system.backend.Controller.User;

import com.system.backend.Dto.User.*;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/user/member") // update detail and password
public class MemberController {
    @Autowired
    private UserService userService;
    //check to update
    @PostMapping
    public String checkPasswordUser(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        String message = userService.checkUserPassword(userCheckPasswordRequest.getPassword()
                , userCheckPasswordRequest.getAccount());
        return message;
    }
    @PutMapping("/setpass")
    public String setPasswordUser(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        String message = userService.setUserPassword(userCheckPasswordRequest.getPassword()
                , userCheckPasswordRequest.getAccount());
        return message;
    }
    // update inf by account
    @PutMapping
    public String updateDetailUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        String message = userService.updateUser(userUpdateRequest);
        return message;
    }
    //get information user by account
    @GetMapping("/singleuser")
    public ResponseEntity<UserResponse> getUser(@RequestBody AccountRequest accountRequest) {
        UserResponse user = userService.getUser(accountRequest.getAccount());
        return ResponseEntity.ok(user);
    }
    // end-update
}
