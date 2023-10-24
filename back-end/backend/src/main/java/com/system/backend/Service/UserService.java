package com.system.backend.Service;

import com.system.backend.Dto.User.*;

import java.util.List;

public interface UserService {
    String addUser(UserRegisterRequest userRegisterRequest);
    LoginMessage loginUser(UserAuthRequest userAuthRequest);
    public String createToken(UserAuthRequest authRequest) throws Exception;
    // member
    UserResponse getUser(String account);
    String updateUser(UserUpdateRequest userUpdateDTO);
    String checkUserPassword(String password, String account);
    String setUserPassword(String password, String account);

    //staff
    List<UserResponse> getAllUsers();
    UserResponse getUser(Integer user_id);

    String deleteUser(Integer user_id);
    String updateUser(Integer user_id, UserUpdateRequest userUpdateDTO);

}
