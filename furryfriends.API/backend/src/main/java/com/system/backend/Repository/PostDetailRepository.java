package com.system.backend.Repository;

import com.system.backend.Dto.Post.PostResponse;
import com.system.backend.Entity.PostDetail;
import com.system.backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@EnableJpaRepositories
@Transactional
@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Integer> {
    @Query("SELECT p FROM PostDetail p WHERE p.post_id = :post_id")
    PostDetail findPostByPost_Id(@Param("post_id") Integer post_id);
    @Query("SELECT p FROM PostDetail p WHERE p.user.account = :account ORDER BY p.post_id DESC")
    List<PostDetail> findPostByAccount(@Param("account") String account);


    @Query("SELECT p FROM PostDetail p WHERE UPPER(p.title) LIKE CONCAT('%',:title,'%') ORDER BY p.post_id DESC")
    List<PostDetail> findPostsByTitleSORT_DESC(@Param("title") String title);
    @Query("SELECT p FROM PostDetail p ORDER BY p.post_id DESC")
    List<PostDetail> findAllSORT_DESC();

    @Query("SELECT p FROM PostDetail p WHERE UPPER(p.title) LIKE CONCAT('%',:title,'%') ORDER BY p.post_id ASC")
    List<PostDetail> findPostsByTitleSORT_ASC(@Param("title") String title);
    @Query("SELECT p FROM PostDetail p ORDER BY p.post_id ASC")
    List<PostDetail> findAllSORT_ASC();


    @Query("SELECT p FROM PostDetail p WHERE p.user.user_id = :user_id AND p.post_id = :post_id")
    PostDetail findPostsByUser_idAndPost_id(@Param("post_id") Integer post_id,
                                               @Param("user_id") Integer user_id);
    @Modifying
    @Query("DELETE FROM PostDetail post WHERE post.post_id  = :post_id")
    void deletePostDetailByPost_id(@Param("post_id") Integer post_id);
    @Modifying
    @Query("DELETE FROM PostDetail post WHERE post.user.user_id  = :user_id")
    void deletePostDetailByUser_id(@Param("user_id") Integer user_id);
}
