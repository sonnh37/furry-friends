package com.system.backend.Service;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.UserDTO;
import com.system.backend.payload.response.LoginMessage;

public interface UserService {
    String addUser(UserDTO userDTO);
    LoginMessage loginUser(LoginDTO loginDTO);

    String deleteUser(Integer user_id);
}
