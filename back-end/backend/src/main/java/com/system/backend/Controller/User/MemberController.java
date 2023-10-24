package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserAuthRequest;
import com.system.backend.Dto.User.UserCheckPasswordRequest;
import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Dto.User.UserUpdateRequest;
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



    //update
    @PostMapping
    public String checkUserPassword(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        String message = userService.checkUserPassword(userCheckPasswordRequest.getPassword()
                , userCheckPasswordRequest.getAccount());
        return message;
    }
    @PutMapping("/setpass")
    public String setUserPassword(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        String message = userService.setUserPassword(userCheckPasswordRequest.getPassword()
                , userCheckPasswordRequest.getAccount());
        return message;
    }
    // update inf by account
    @PutMapping
    public String updateUserDetail(@RequestBody UserUpdateRequest userUpdateRequest) {
        String message = userService.updateUser(userUpdateRequest);
        return message;
    }
    //get information user by account
    @GetMapping("/singleuser")
    public ResponseEntity<UserResponse> getUser(@RequestBody UserAuthRequest userAuthRequest) {
        UserResponse user = userService.getUser(userAuthRequest);
        return ResponseEntity.ok(user);
    }
    // end-update
}
