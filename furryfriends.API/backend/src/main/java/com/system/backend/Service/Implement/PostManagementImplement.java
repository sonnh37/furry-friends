package com.system.backend.Service.Implement;

import com.system.backend.Entity.PostManagement;
import com.system.backend.Repository.PostManagementRepository;
import com.system.backend.Service.PostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostManagementImplement implements PostManagementService {
    @Autowired
    PostManagementRepository postManagementRepository;
    @Override
    public List<PostManagement> getPostManagementByUser_id(Integer user_id) {
        List<PostManagement> postManagement = null;
        postManagement =postManagementRepository.findPostManagementByPost_id(user_id);
        return postManagement;
    }
}
