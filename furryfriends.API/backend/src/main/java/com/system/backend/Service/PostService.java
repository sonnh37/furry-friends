package com.system.backend.Service;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;

import java.util.List;

public interface PostService {
    String insertPost(String account, PostRequest postRequest);
    PostResponse getPost(Integer product_id);
    List<PostResponse> getAllPosts_desc();
    List<PostResponse> getPostBySearch_desc(String q);

    List<PostResponse> getAllPosts_asc();
    List<PostResponse> getPostBySearch_asc(String q);



    List<PostResponse> getAllPostsByAccount(String account);
    String deletePost(String account, Integer post_id);
    String deleteDataByStaff(Integer post_id);

    String updatePost(String account, Integer post_id, PostRequest postRequest);
}
