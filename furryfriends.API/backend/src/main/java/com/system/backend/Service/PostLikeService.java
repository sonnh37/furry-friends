package com.system.backend.Service;

public interface PostLikeService {
    String insertLike(String account, Integer post_id);
    String deleteLike(String account, Integer post_id);
}
