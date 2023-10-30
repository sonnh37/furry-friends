package com.system.backend.Controller.Post;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Service.PostService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.POSTUP)
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping(Link.USER.POSTCRUD.POST)
    public String addProduct(
            @PathVariable String account, @RequestBody PostRequest postRequest){
        String mess = postService.insertPost(account,postRequest);
        return mess;
    }
}
