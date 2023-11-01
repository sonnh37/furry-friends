package com.system.backend.Service.Implement;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Entity.*;
import com.system.backend.Repository.PostDetailRepository;
import com.system.backend.Repository.PostManagementRepository;
import com.system.backend.Repository.ProductRepository;
import com.system.backend.Repository.UserRepository;
import com.system.backend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private PostDetail getPostHasFromUser(String account, Integer post_id){
        User user = userRepository.findUserByAccount(account);
        PostDetail postDetail = null;
        if(user != null){
            postDetail = postDetailRepository.findPostsByUser_idAndPost_id(post_id, user.getUser_id());
        }
        return postDetail;
    }
    @Override
    public String deletePost(String account, Integer post_id) {
        String mess = "";

        PostDetail pExist = this.getPostHasFromUser(account, post_id);
        if (pExist != null) {
            //post_management
            postManagementRepository.deletePostManagementByPost_id(post_id);
            //post_detail
            postDetailRepository.deletePostDetailByPost_id(post_id);
            mess = "Xoa thanh cong";
        } else{
            mess = "post not exist";
        }
        return mess;
    }
    @Override
    public String updatePost(String account, Integer post_id, PostRequest postRequest) {
        String mess = "";
        PostDetail pExist = this.getPostHasFromUser(account, post_id);
        if (pExist != null) {
            updatePostDetail(pExist, postRequest);
            mess = "Cap nhat thanh cong";
        } else{
            mess = "post not exist";
        }
        return mess;
    }
    public void updatePostDetail(PostDetail pExist, PostRequest postRequest){
        pExist.setTitle(postRequest.getTitle());
        pExist.setContent(postRequest.getContent());
        pExist.setPublishDate(getDateNow());
        pExist.setImage(postRequest.getImg());

        postDetailRepository.save(pExist);
    }

    @Override
    public PostResponse getPost(Integer post_id) {
        PostDetail postDetail = postDetailRepository.findPostByPost_Id(post_id);
        PostResponse postResponse = new PostResponse();
        if(postDetail != null){
            postResponse = convertPostToPostResponse(postDetail);
        }
        return postResponse;
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<PostDetail> list = new ArrayList<>();
        List<PostResponse> listConvert = new ArrayList<>();
        list = postDetailRepository.findAll();
        for (PostDetail p:
                list) {
            listConvert.add(convertPostToPostResponse(p));
        }
        return listConvert;
    }
    public PostResponse convertPostToPostResponse(PostDetail postDetail){
        if(postDetail == null){
            return PostResponse.builder().build();
        }
        // convert List<Img_Product> to List<String>

        return PostResponse.builder()
                .post_id(postDetail.getPost_id())
                .user_id(postDetail.getUser().getUser_id())
                .account(postDetail.getUser().getAccount())
                .user_name(postDetail.getUser().getFirst_name() + " " + postDetail.getUser().getLast_name())
                .title(postDetail.getTitle())
                .content(postDetail.getContent())
                .publishdate(postDetail.getPublishDate())
                .img(postDetail.getImage())
                .build();
    }
}
