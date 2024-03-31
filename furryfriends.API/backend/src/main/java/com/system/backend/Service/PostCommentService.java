package com.system.backend.Service;

import com.system.backend.Dto.PostComment.PostCommentRequest;
import com.system.backend.Dto.PostComment.PostCommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostCommentService {
    String insertComment(String account, Integer post_id, PostCommentRequest postCommentRequest);
    String deleteComment(String account, Integer post_id);
    String deleteCommentByPostAndCommentId(Integer post_id, Integer comment_id);
    List<PostCommentResponse> getAllCommentByPost_id(Integer post_id);
}
