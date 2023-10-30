package com.system.backend.Controller.User;

import com.system.backend.Dto.User.*;
import com.system.backend.Service.UserService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.MEMBER) // update detail and password
public class MemberController {
    @Autowired
    private UserService userService;

    //get information user by account
    @GetMapping(Link.USER.MEMBERCRUD.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable String account) {
        UserResponse user = userService.getMemberByAccount(account);
        return ResponseEntity.ok(user);
    }
    @GetMapping(Link.USER.MEMBERCRUD.GETONEALLROLE)
    public ResponseEntity<UserResponse> getUserAllRole(@PathVariable String account) {
        UserResponse user = userService.getMemberByAccountAllRole(account);
        return ResponseEntity.ok(user);
    }

    // update inf by account
    @PutMapping(Link.USER.MEMBERCRUD.PUT)
    public String setPasswordUser(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        String message = userService.setMemberPassword(userCheckPasswordRequest.getPassword()
                , userCheckPasswordRequest.getAccount());
        return message;
    }

    @PutMapping
    public String updateDetailUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        String message = userService.updateMember(userUpdateRequest);
        return message;
    }
    //check to update
    @PostMapping
    public String checkPasswordUser(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        String message = userService.checkMemberPassword(userCheckPasswordRequest.getPassword()
                , userCheckPasswordRequest.getAccount());
        return message;
    }



    // end-update
}
