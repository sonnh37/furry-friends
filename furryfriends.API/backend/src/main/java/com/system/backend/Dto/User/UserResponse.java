package com.system.backend.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Integer user_id;
    private Integer role_id;
    private String account;
    private String email;
    private String first_name;
    private String last_name;
    private String phone;
    private String address;
    private String birth;
    private String sex;
}
