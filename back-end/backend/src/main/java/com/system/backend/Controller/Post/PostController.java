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
    @PutMapping(Link.USER.POSTCRUD.PUT)
    public String updatePost(@PathVariable String account,
                                @PathVariable Integer post_id,
                                @RequestBody PostRequest postRequest)
    {
        String mess = postService.updatePost(account,post_id,postRequest);
        return mess;
    }

    @DeleteMapping(Link.USER.POSTCRUD.DELETE)
    public String deletePost(@PathVariable String account,
                                @PathVariable Integer post_id)
    {
        String mess = postService.deletePost(account,post_id);
        return mess;
    }
    @PostMapping(Link.USER.POSTCRUD.POST)
    public String addPost(
            @PathVariable String account, @RequestBody PostRequest postRequest){
        String mess = postService.insertPost(account,postRequest);
        return mess;
    }
}
