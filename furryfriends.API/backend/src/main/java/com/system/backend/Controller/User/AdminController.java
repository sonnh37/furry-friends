package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserRegisterRequest;
import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Service.UserService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.ADMIN)
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllStaff() {
        List<UserResponse> list = userService.getAllStaff();
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public String AddStaff(@RequestBody UserRegisterRequest userRegisterRequest)
    {
        return userService.addStaff(userRegisterRequest);
    }
    @PostMapping(Link.USER.ADMINCRUD.POSTADMIN)
    public String AddAdmin(@RequestBody UserRegisterRequest userRegisterRequest)
    {
        return userService.addAdmin(userRegisterRequest);
    }
}
