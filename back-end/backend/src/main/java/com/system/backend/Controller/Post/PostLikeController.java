package com.system.backend.Controller.Post;

import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Service.PostLikeService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.POSTLIKE)
public class PostLikeController {
    @Autowired
    private PostLikeService postLikeService;
    @PostMapping(Link.USER.POST_LIKE_CRUD.POST)
    public String insertLike(@PathVariable String account,
                             @PathVariable Integer post_id )
    {
        String mess = postLikeService.insertLike(account, post_id);
        return mess;
    }
    @DeleteMapping(Link.USER.POST_LIKE_CRUD.DELETE)
    public String deleteLike(@PathVariable String account,
                             @PathVariable Integer post_id)
    {
        String mess = postLikeService.deleteLike(account,post_id);
        return mess;
    }
}
