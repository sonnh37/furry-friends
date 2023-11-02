package com.system.backend.Controller.Post;

import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Dto.PostComment.PostCommentRequest;
import com.system.backend.Dto.PostComment.PostCommentResponse;
import com.system.backend.Service.PostCommentService;
import com.system.backend.Service.PostLikeService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.POSTCOMMENT)
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;
    @PostMapping(Link.USER.POST_CMT_CRUD.POST)
    public String insertComment(@PathVariable String account,
                                @PathVariable Integer post_id, @RequestBody PostCommentRequest postCommentRequest)
    {
        String mess = postCommentService.insertComment(account, post_id, postCommentRequest);
        return mess;
    }
    @DeleteMapping(Link.USER.POST_CMT_CRUD.DELETE)
    public String deleteComment(@PathVariable Integer post_id,
                                @PathVariable Integer comment_id

    )
    {
        String mess = postCommentService.deleteCommentByPostAndCommentId(post_id, comment_id);
        return mess;
    }
    @GetMapping(Link.USER.POST_CMT_CRUD.GETALLL)
    public ResponseEntity<List<PostCommentResponse>> getAllPost(@PathVariable Integer post_id)
    {
        List<PostCommentResponse> list = postCommentService.getAllCommentByPost_id(post_id);
        return ResponseEntity.ok(list);
    }
}
