package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Dto.User.UserUpdateRequest;
import com.system.backend.Entity.User;
import com.system.backend.Service.UserService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT+Link.USER.STAFF)
public class StaffController { //member(view users(getUsers), delete, update),product, post
    @Autowired
    private UserService userService;

    //get one user
    @GetMapping(Link.USER.STAFFCRUD.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer user_id) {
        UserResponse user = userService.getMemberById(user_id);
        return ResponseEntity.ok(user);
    }

    //get allUsers
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllMember() {
        List<UserResponse> list = userService.getAllMember();
        return ResponseEntity.ok(list);
    }

    //update detail
    @PutMapping(Link.USER.STAFFCRUD.PUT)
    public String updateDetailUser(@PathVariable Integer user_id,
                                   @RequestBody UserUpdateRequest userUpdateDTO) {
        String message = userService.updateMemberById(user_id, userUpdateDTO);
        return message;
    }

    //dellete
    @DeleteMapping(Link.USER.STAFFCRUD.DELETE)
    public String deleteUser(@PathVariable Integer user_id) {
        String message = userService.deleteMemberById(user_id);
        return message;
    }


}
