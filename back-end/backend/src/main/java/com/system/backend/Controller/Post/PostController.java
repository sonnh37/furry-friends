package com.system.backend.Controller.Post;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Service.PostService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.POSTUP)
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(Link.USER.POSTCRUD.GETALLL)
    public ResponseEntity<List<PostResponse>> getAllPost()
    {
        List<PostResponse> list = postService.getAllPosts();
        return ResponseEntity.ok(list);
    }
    @GetMapping(Link.USER.POSTCRUD.GET)
    public ResponseEntity<PostResponse> getPostById( @PathVariable Integer post_id )
    {
        PostResponse postResponse = postService.getPost(post_id);
        return ResponseEntity.ok(postResponse);
    }
    @PostMapping(Link.USER.POSTCRUD.POST)
    public String addPost(
            @PathVariable String account, @RequestBody PostRequest postRequest){
        String mess = postService.insertPost(account,postRequest);
        return mess;
    }
}
