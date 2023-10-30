package com.system.backend.Repository;

import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Entity.PostDetail;
import com.system.backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Integer> {
    @Query("SELECT p FROM PostDetail p WHERE p.post_id = :post_id")
    PostDetail findPostByPost_Id(@Param("post_id") Integer post_id);
    @Query("SELECT p FROM Product p WHERE p.user.user_id = :user_id")
    List<Product> findProductsByUser_id(@Param("user_id") Integer user_id);
}
