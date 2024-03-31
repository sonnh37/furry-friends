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
    // getall searchbytitle, desc
    @GetMapping(Link.USER.POSTCRUD.GETALLL_DESC)
    public ResponseEntity<List<PostResponse>> getAllPost_desc()
    {
        List<PostResponse> list = postService.getAllPosts_desc();
        return ResponseEntity.ok(list);
    }
    @GetMapping(Link.USER.POSTCRUD.GETBYSEARCH_DESC)
    public ResponseEntity<List<PostResponse>> getPostBySearch_desc(@RequestParam String q)
    {
        List<PostResponse> list = postService.getPostBySearch_desc(q);
        return ResponseEntity.ok(list);
    }

    // getall searchbytitle, asc
    @GetMapping(Link.USER.POSTCRUD.GETALLL_ASC)
    public ResponseEntity<List<PostResponse>> getAllPost_asc()
    {
        List<PostResponse> list = postService.getAllPosts_asc();
        return ResponseEntity.ok(list);
    }
    @GetMapping(Link.USER.POSTCRUD.GETBYSEARCH_ASC)
    public ResponseEntity<List<PostResponse>> getPostBySearch_ascS(@RequestParam String q)
    {
        List<PostResponse> list = postService.getPostBySearch_asc(q);
        return ResponseEntity.ok(list);
    }




    @GetMapping(Link.USER.POSTCRUD.GETALLLBYACCOUNT)
    public ResponseEntity<List<PostResponse>> getAllPostByAccount(@PathVariable String account)
    {
        List<PostResponse> list = postService.getAllPostsByAccount(account);
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
