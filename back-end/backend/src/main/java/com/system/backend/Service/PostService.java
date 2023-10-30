package com.system.backend.Service;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;

import java.util.List;

public interface PostService {
    String insertPost(String account, PostRequest postRequest);
    PostResponse getPost(Integer product_id);
    List<PostResponse> getAllPosts();
}
