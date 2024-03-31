package com.system.backend.Service.Implement;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Entity.*;
import com.system.backend.Repository.*;
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
    private PostLikeRepository postLikeRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private PostManagementRepository postManagementRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImgProductRepository imgProductRepository;
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
                    .totalLike(0)
                    .totalComment(0)
                    .image(postRequest.getImage())
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
            User user = userRepository.findUserByAccount(account);

            // post_like
            postLikeRepository.deleteLikeByPost_id(post_id);
            //post_comment
            postCommentRepository.deleteCommentByPost_id(post_id);
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
    public String deleteDataByStaff(Integer post_id) {
        String mess = "";
        // post_like
        postLikeRepository.deleteLikeByPost_id(post_id);
        //post_comment
        postCommentRepository.deleteCommentByPost_id(post_id);
        //post_management
        postManagementRepository.deletePostManagementByPost_id(post_id);
        //post_detail
        postDetailRepository.deletePostDetailByPost_id(post_id);
        mess = "Xoa thanh cong";
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
        pExist.setImage(postRequest.getImage());

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
    public List<PostResponse> getAllPosts_desc() {
        List<PostDetail> list = new ArrayList<>();
        List<PostResponse> listConvert = new ArrayList<>();
        list = postDetailRepository.findAllSORT_DESC();
        for (PostDetail p:
                list) {
            listConvert.add(convertPostToPostResponse(p));
        }
        return listConvert;
    }
    @Override
    public List<PostResponse> getPostBySearch_desc(String q) {
        List<PostDetail> list = new ArrayList<>();
        List<PostResponse> listConvert = new ArrayList<>();
        list = postDetailRepository.findPostsByTitleSORT_DESC(q);
        for (PostDetail p:
                list) {
            listConvert.add(convertPostToPostResponse(p));
        }
        return listConvert;
    }


    @Override
    public List<PostResponse> getAllPosts_asc() {
        List<PostDetail> list = new ArrayList<>();
        List<PostResponse> listConvert = new ArrayList<>();
        list = postDetailRepository.findAllSORT_ASC();
        for (PostDetail p:
                list) {
            listConvert.add(convertPostToPostResponse(p));
        }
        return listConvert;
    }
    @Override
    public List<PostResponse> getPostBySearch_asc(String q) {
        List<PostDetail> list = new ArrayList<>();
        List<PostResponse> listConvert = new ArrayList<>();
        list = postDetailRepository.findPostsByTitleSORT_ASC(q);
        for (PostDetail p:
                list) {
            listConvert.add(convertPostToPostResponse(p));
        }
        return listConvert;
    }

    @Override
    public List<PostResponse> getAllPostsByAccount(String account) {
        List<PostDetail> list = new ArrayList<>();
        List<PostResponse> listConvert = new ArrayList<>();
        //User user = userRepository.findUserByAccount(account);

        list = postDetailRepository.findPostByAccount(account);
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
                .total_like(postDetail.getTotalLike())
                .total_comment(postDetail.getTotalComment())
                .user_name(postDetail.getUser().getFirst_name() + " " + postDetail.getUser().getLast_name())
                .title(postDetail.getTitle())
                .content(postDetail.getContent())
                .publishdate(postDetail.getPublishDate())
                .image(postDetail.getImage())
                .build();
    }



}
