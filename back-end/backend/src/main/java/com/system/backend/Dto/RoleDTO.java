package com.system.backend.Dto;

public class RoleDTO {
    private Integer roleID;
    private String role;

    public RoleDTO(Integer roleID, String role) {
        this.roleID = roleID;
        this.role = role;
    }

    public RoleDTO() {
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
