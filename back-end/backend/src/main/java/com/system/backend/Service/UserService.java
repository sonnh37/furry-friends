package com.system.backend.Service;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.UserDTO;
import com.system.backend.Enity.User;
import com.system.backend.payload.response.LoginMessage;

import java.util.List;

public interface UserService {
    String addUser(UserDTO userDTO);
    LoginMessage loginUser(LoginDTO loginDTO);
    List<User> getUser();
    String deleteUser(Integer user_id);
    String updateUser(String account, UserDTO userDTO);
    String checkUserPassword(String password, String account);
    String setUserPassword(String password, String account);
}
