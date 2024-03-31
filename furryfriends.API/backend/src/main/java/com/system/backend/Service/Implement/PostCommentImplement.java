package com.system.backend.Service.Implement;

import com.system.backend.Dto.PostComment.PostCommentRequest;
import com.system.backend.Dto.PostComment.PostCommentResponse;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Entity.*;
import com.system.backend.Repository.*;
import com.system.backend.Service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostCommentImplement implements PostCommentService {
    @Autowired
    private PostDetailRepository postDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;
    public String getDateNow() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
    @Override
    public String insertComment(String account, Integer post_id, PostCommentRequest postCommentRequest) {
        // khong truyen tham so product_id
        String mess = "";
            User u = userRepository.findUserByAccount(account);
            PostDetail postDetail = postDetailRepository.findPostByPost_Id(post_id);
            if (u != null && postDetail != null) { // ton tai
                if(postCommentRequest.getComment() == null){
                    postCommentRequest.setComment("");
                }
                PostComment postComment = PostComment.builder()
                        .user(u) // người comment
                        .comment(postCommentRequest.getComment())
                        .postDetail(postDetail)
                        .publishDateComment(getDateNow())
                        .build();
                postCommentRepository.save(postComment);
                // cap nhat số cmt
                List<PostComment> postCommentList = new ArrayList<>();
                postCommentList = postCommentRepository.findCommentByPost_id(post_id);
                postDetail.setTotalComment(postCommentList.size());
                postDetailRepository.save(postDetail);

                mess = "Them thanh cong";
            } else{
                mess = "Nguoi dung khong ton tai hoac post khong ton tai";
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
    public String deleteComment(String account, Integer post_id) {
        String mess = "";

        PostDetail pExist = this.getPostHasFromUser(account, post_id);
        if (pExist != null) {
            User user = userRepository.findUserByAccount(account);
            //xoa like cua postLike dua theo account va post_id
            postCommentRepository.deleteCommentByPost_idAndUser_id(post_id, user.getUser_id());
            // tang 1 like trong totallike

            pExist.setTotalComment(pExist.getTotalComment() - 1);
            postDetailRepository.save(pExist);
            mess = "Unlike thanh cong";
        } else{
            mess = "post not exist";
        }
        return mess;
    }
    @Override
    public String deleteCommentByPostAndCommentId(Integer post_id, Integer comment_id) {
        String mess = "";
        PostDetail pExist = postDetailRepository.findPostByPost_Id(post_id); // post của owner post
        if (pExist != null) {
            postCommentRepository.deleteCommentByComment_id(comment_id);
            List<PostComment> postCommentList = new ArrayList<>();
            postCommentList = postCommentRepository.findCommentByPost_id(post_id);
            pExist.setTotalComment(postCommentList.size());
            postDetailRepository.save(pExist);
            mess = "Xoa thanh cong";
        } else{
            mess = "post not exist";
        }
        return mess;
    }

    @Override
    public List<PostCommentResponse> getAllCommentByPost_id(Integer post_id) {
        List<PostComment> list = new ArrayList<>();
        List<PostCommentResponse> listConvert = new ArrayList<>();
        list = postCommentRepository.findCommentByPost_id(post_id);
        for (PostComment p:
                list) {
            listConvert.add(convertRequestToResponse(p));
        }
        return listConvert;
    }
    public PostCommentResponse convertRequestToResponse(PostComment postComment){
        if(postComment == null){
            return PostCommentResponse.builder().build();
        }

        return PostCommentResponse.builder()
                .comment_id(postComment.getCommentID())
                .post_id(postComment.getPostDetail().getPost_id())
                .user_id(postComment.getUser().getUser_id())
                .account(postComment.getUser().getAccount())
                .user_name(postComment.getUser().getFirst_name() + " " + postComment.getUser().getLast_name())
                .comment(postComment.getComment())
                .publish_date_comment(postComment.getPublishDateComment())
                .build();
    }
}
