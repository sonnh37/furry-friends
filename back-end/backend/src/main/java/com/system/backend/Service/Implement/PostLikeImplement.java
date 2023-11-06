package com.system.backend.Service.Implement;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Entity.*;
import com.system.backend.Repository.PostDetailRepository;
import com.system.backend.Repository.PostLikeRepository;
import com.system.backend.Repository.PostManagementRepository;
import com.system.backend.Repository.UserRepository;
import com.system.backend.Service.PostLikeService;
import com.system.backend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostLikeImplement  implements PostLikeService {
    @Autowired
    private PostDetailRepository postDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostManagementRepository postManagementRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;

    private boolean isDoNotHaveAnyLike(String account, Integer post_id){
        boolean isValid = false;
        User user = userRepository.findUserByAccount(account);
        if(user != null){
            PostLike postLike = postLikeRepository.findLikeByPost_idAndUser_id(post_id, user.getUser_id());
            if(postLike== null){
                isValid = true;
            }
        }
        return  isValid;
    }
    @Override
    public String insertLike(String account, Integer post_id) {
        // khong truyen tham so product_id
        String mess = "";
        if(isDoNotHaveAnyLike(account, post_id)){
            User u = userRepository.findUserByAccount(account);
            PostDetail postDetail = postDetailRepository.findPostByPost_Id(post_id);
            if (u != null && postDetail != null) { // ton tai
                PostLike postLike = PostLike.builder()
                        .user(u)
                        .postDetail(postDetail)
                        .status(true)
                        .build();
                postLikeRepository.save(postLike);
                // tang 1 like trong totallike
                // cap nhat số cmt
                List<PostLike> postLikeList = new ArrayList<>();
                postLikeList = postLikeRepository.findLikeByPost_id(post_id);
                postDetail.setTotalLike(postLikeList.size());
                postDetailRepository.save(postDetail);

                mess = "Them like thanh cong";
            } else{
                mess = "Nguoi dung khong ton tai hoac post khong ton tai";
            }
        } else{
            mess = "Da like";
        }
        return mess;
    }
    private PostDetail getPostHasFromUser(String account, Integer post_id){
        User user = userRepository.findUserByAccount(account);
        PostDetail postDetail = null;
        if(user != null){
            postDetail = postDetailRepository.findPostByPost_Id(post_id);
        }
        return postDetail;
    }
    @Override
    public String deleteLike(String account, Integer post_id) {
        String mess = "";

        PostDetail postDetail = this.getPostHasFromUser(account, post_id);
        if (postDetail != null) {
            User user = userRepository.findUserByAccount(account);

            //xoa like cua postLike dua theo account va post_id
            postLikeRepository.deleteLikeByPost_idAndUser_id(post_id, user.getUser_id());
            System.out.println(post_id + ", "+ user.getUser_id());
            // tang 1 like trong totallike
            List<PostLike> postLikeList = new ArrayList<>();
            postLikeList = postLikeRepository.findLikeByPost_id(post_id);
            postDetail.setTotalLike(postLikeList.size());
            postDetailRepository.save(postDetail);
            mess = "Unlike thanh cong";
        } else{
            mess = "post not exist";
        }
        return mess;
    }
}
