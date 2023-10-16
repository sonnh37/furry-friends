package com.system.backend.Service.Implement;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.UserDTO;
import com.system.backend.Enity.User;
import com.system.backend.Repository.UserRepo;
import com.system.backend.Service.UserService;
import com.system.backend.payload.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplement implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        User userExist = userRepo.findUserByAccount(userDTO.getAccount()); //
        if(userExist == null){ // add new
            userExist = new User(null,
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
            userRepo.save(userExist);
        } else{
            return "tai khoan da ton tai!";
        }
        System.out.println(userDTO.getFirst_name());
        System.out.println(this.passwordEncoder.encode(userDTO.getPassword()
        ));

        return userExist.getFirst_name();

    }

    UserDTO userDTO;
    @Override
    public LoginMessage  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user = userRepo.findUserByAccount(loginDTO.getAccount());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            //check pass from user and encoded Password system
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepo.findUserByAccountAndPassword(loginDTO.getAccount(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Account not exits", false);
        }
    }

    @Override
    public List<User> getUser() {
        List<User> list = userRepo.findAll();
        return list;
    }

    @Override
    public String deleteUser(Integer user_id) {
        String mess = "";
        User user = userRepo.findByUser_id(user_id);
        if(user != null){
            userRepo.delete(user);
            mess = "deleted";
        } else{
            mess = "not exist";
        }
        return mess;
    }

    @Override
    public String updateUser(Integer user_id, UserDTO userDTO) {
        String mess = "";
        User user = userRepo.findByUser_id(user_id);
        // nếu như tồn tại cái cũ thì check và update cái mới userDTO
        if(user != null){
            String newFirstNameUser = this.updateUserDetail(user_id, userDTO);
            mess = "updated: " + newFirstNameUser;
        } else{
            mess = "not exist";
        }
        return mess;
    }



    public String updateUserDetail(Integer user_id, UserDTO userDTO) {
        User userUpdate = new User();
        userUpdate.setUser_id(user_id);
        userUpdate.setRole(userRepo.findByRoleId(userDTO.getRole_id()));
        userUpdate.setAccount(userDTO.getAccount());
        userUpdate.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        userUpdate.setEmail(userDTO.getEmail());
        userUpdate.setFirst_name(userDTO.getFirst_name());
        userUpdate.setLast_name(userDTO.getLast_name());
        userUpdate.setPhone(userDTO.getPhone());
        userUpdate.setAddress(userDTO.getAddress());
        userUpdate.setBirth(userDTO.getBirth());
        userUpdate.setSex(userDTO.getSex());
        userRepo.save(userUpdate);
        return userUpdate.getFirst_name();

    }

}
