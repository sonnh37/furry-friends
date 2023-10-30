package com.system.backend.Service;

import com.system.backend.Dto.Post.PostRequest;
import com.system.backend.Dto.Product.ProductRequest;

public interface PostService {
    String insertPost(String account, PostRequest postRequest);
}
