package com.system.backend.Dto;

public class UpdateUserRequest {
    private String account;
    private UserDTO userDTO;

    // getters và setters

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String account, UserDTO userDTO) {
        this.account = account;
        this.userDTO = userDTO;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}

