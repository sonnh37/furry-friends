package com.system.DogCatSystem.controller;

import com.system.DogCatSystem.exception.UserNotFoundException;
import com.system.DogCatSystem.model.User;
import com.system.DogCatSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    //register
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return  userRepository.save(newUser);
    }
    //display
    @GetMapping("/users")
    List<User> getUsers(){
        return  userRepository.findAll();
    }
    //find
    @GetMapping("/user/{userId}")
    User getUserById(@PathVariable Long userId){
        return  userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));
    }
    //login
    @PostMapping("/login")
    User getUserById(@RequestBody User user){
        List<User> list = userRepository.findAll();
        for(User u: list){
            if(user.getEmail().equalsIgnoreCase(u.getEmail()) &&
                    user.getPassword().equalsIgnoreCase(u.getPassword())){
                return u;
            }
        }
        return  null;
    }
    // update
    @PutMapping("/user/{userId}")
    User updateUser(@RequestBody User newUser, @PathVariable Long userId){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setAddress(newUser.getAddress());
                    user.setAge(newUser.getAge());
                    user.setPassword(newUser.getPassword());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setPhone(newUser.getPhone());
                    user.setSex(newUser.isSex());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(userId));
    }

    //Delete
    @DeleteMapping("/user/{userId}")
    String deleteUser(@PathVariable Long userId){
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
        return "User with id " + userId+ " has been deleted success.";
    }
}
