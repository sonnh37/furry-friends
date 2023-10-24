package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Dto.User.UserUpdateRequest;
import com.system.backend.Entity.User;
import com.system.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/user/staff")
public class StaffController { //member(view users(getUsers), delete, update),product, post
    @Autowired
    private UserService userService;
    //dellete
    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable Integer user_id) {
        String message = userService.deleteUser(user_id);
        return message;
    }
    //update detail
    @PutMapping("/{user_id}")
    public String updateDetailUser(@PathVariable Integer user_id,@RequestBody UserUpdateRequest userUpdateDTO) {
        String message = userService.updateUser(user_id, userUpdateDTO);
        return message;
    }
    //get allUsers
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> list = userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    //get one user
    @GetMapping("/singleuser/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer user_id) {
        UserResponse user = userService.getUser(user_id);
        return ResponseEntity.ok(user);
    }




}
