package com.system.backend.Controller.Dashboard;

import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Service.PostService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.STATISTIC)
public class DashboardForPost {
    @Autowired
    private PostService postService;
    @GetMapping(Link.USER.STATISTICCRUD.STATISTICPOST)
    public Integer getAllPost_asc()
    {
        List<PostResponse> list = postService.getAllPosts_asc();
        return list.size();
    }
    @GetMapping(Link.USER.STATISTICCRUD.STATISTICPOSTLIKE)
    public Integer getAllPostLike_asc()
    {
        Integer count = 0;
        List<PostResponse> list = postService.getAllPosts_asc();
        for(int i = 0; i < list.size(); i++){
            count += list.get(i).getTotal_like();
        }
        return count;
    }
    @GetMapping(Link.USER.STATISTICCRUD.STATISTICPOSTCOMMENT)
    public Integer getAllPostComment_asc()
    {
        Integer count = 0;
        List<PostResponse> list = postService.getAllPosts_asc();
        for(int i = 0; i < list.size(); i++){
            count += list.get(i).getTotal_comment();
        }
        return count;
    }
}
