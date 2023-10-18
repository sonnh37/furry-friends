package com.system.backend.Controller.User;


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
@RequestMapping("api/v1/user/staff")
public class StaffController { //member(view users(getUsers), delete, update),product, post
    @Autowired
    private UserService userService;
    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable Integer user_id) {
        String message = userService.deleteUser(user_id);
        return message;
    }
    @PutMapping
    public String updateUserDetail(@RequestBody UpdateUserRequest updateUserRequest) {
        String message = userService.updateUser(updateUserRequest.getAccount(), updateUserRequest.getUserDTO());
        return message;
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> list = userService.getUsers();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/singleuser/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable Integer user_id) {
        User user = userService.getUser(user_id);
        return ResponseEntity.ok(user);
    }




}
