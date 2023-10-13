package com.system.backend.Service.Implement;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.RoleDTO;
import com.system.backend.Dto.UserDTO;
import com.system.backend.Enity.Role;
import com.system.backend.Enity.User;
import com.system.backend.Repository.UserRepo;
import com.system.backend.Service.UserService;
import com.system.backend.payload.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserImplement implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(userDTO.getUser_id(),
                userRepo.findByRoleId(userDTO.getRole_id()),
                userDTO.getAccount(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getEmail(),
                userDTO.getFirst_name(),
                userDTO.getLast_name(),
                userDTO.getPhone(),
                userDTO.getAddress(),
                userDTO.getBirth(),
                userDTO.getSex()
        );
        System.out.println(userDTO.getFirst_name());
        System.out.println(this.passwordEncoder.encode(userDTO.getPassword()
        ));
        userRepo.save(user);
        return user.getFirstName();

    }

    UserDTO userDTO;
    @Override
    public LoginMessage  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user = userRepo.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            //check pass from user and encoded Password system
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }

    @Override
    public String deleteUser(Integer user_id) {
        String mess = "";
        User user = userRepo.findByUserID(user_id);
        if(user != null){
            userRepo.delete(user);
            mess = "deleted";
        } else{
            mess = "not exist";
        }
        return mess;
    }


}
