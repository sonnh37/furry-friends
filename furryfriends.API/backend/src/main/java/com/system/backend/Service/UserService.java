package com.system.backend.Service;

import com.system.backend.Dto.User.*;

import java.util.List;

public interface UserService {
    String addUser(UserRegisterRequest userRegisterRequest);

    LoginMessage loginUser(UserAuthRequest userAuthRequest);
    String createToken(UserAuthRequest authRequest) throws Exception;
    // member
    UserResponse getMemberByAccount(String account);
    UserResponse getMemberByAccountAllRole(String account);
    String updateMember(UserUpdateRequest userUpdateDTO);
    String checkMemberPassword(String password, String account);
    String setMemberPassword(String password, String account);

    //staff
    List<UserResponse> getAllMember();
    UserResponse getMemberById(Integer user_id);

    String deleteMemberById(Integer user_id);
    String updateMemberById(Integer user_id, UserUpdateRequest userUpdateDTO);

    //admin
    List<UserResponse> getAllStaff();
    String addStaff(UserRegisterRequest userRegisterRequest);
    String addAdmin(UserRegisterRequest userRegisterRequest);
    String deletePostByStaff(Integer post_id);
    String deleteProductByStaff(Integer product_id);
    String deletePetByStaff(Integer pet_id);


}
