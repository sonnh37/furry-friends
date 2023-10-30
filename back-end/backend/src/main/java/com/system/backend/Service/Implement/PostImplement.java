package com.system.backend.Service.Implement;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Entity.*;
import com.system.backend.Repository.PostDetailRepository;
import com.system.backend.Repository.PostManagementRepository;
import com.system.backend.Repository.ProductRepository;
import com.system.backend.Repository.UserRepository;
import com.system.backend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostImplement implements PostService {
    @Autowired
    private PostDetailRepository postDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostManagementRepository postManagementRepository;
    public String getDateNow() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
    @Override
    public String insertPost(String account, PostRequest postRequest) {
        //xu li date
        postRequest.setPublishdate(getDateNow());

        // khong truyen tham so product_id
        String mess = "";
        User u = userRepository.findUserByAccount(account);
        if (u != null) { // ton tai
            PostDetail postDetail = PostDetail.builder()
                    .user(u)
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .publishDate(postRequest.getPublishdate())
                    .image(postRequest.getImg())
                    .build();
            PostManagement postManagement = PostManagement.builder()
                    .user(u)
                    .role(u.getRole())
                    .postDetail(postDetail)
                    .build();
            if(postDetail!=null){
                postDetailRepository.save(postDetail);
                postManagementRepository.save(postManagement);
                mess = "Them post thanh cong";
            } else{
                mess = "Nguoi dung chua nhap du lieu";
            }

        } else{
            mess = "Nguoi dung khong ton tai";
        }


        return mess;
    }
}
