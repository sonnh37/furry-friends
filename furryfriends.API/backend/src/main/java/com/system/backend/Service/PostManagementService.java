package com.system.backend.Service;

import com.system.backend.Entity.PostManagement;

import java.util.List;

public interface PostManagementService {
    List<PostManagement> getPostManagementByUser_id(Integer user_id);
}
