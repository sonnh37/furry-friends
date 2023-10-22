package com.system.backend.Service;

import com.system.backend.Dto.User.UserRegisterRequestDTO;
import com.system.backend.Dto.User.UserAuthRequestDTO;
import com.system.backend.Dto.User.UserUpdateRequestDTO;
import com.system.backend.Entity.User;
import com.system.backend.payload.response.LoginMessage;

import java.util.List;

public interface UserService {
    String addUser(UserRegisterRequestDTO userRegisterRequestDTO);
    LoginMessage loginUser(UserAuthRequestDTO userAuthRequestDTO);
    public String createToken(UserAuthRequestDTO authRequest) throws Exception;
    List<User> getUsers();
    User getUser(Integer user_id);
    String deleteUser(Integer user_id);
    String updateUser(Integer user_id, UserUpdateRequestDTO userUpdateDTO);
    String checkUserPassword(String password, String account);
    String setUserPassword(String password, String account);
}
