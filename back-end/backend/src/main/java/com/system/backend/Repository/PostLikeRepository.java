package com.system.backend.Repository;

import com.system.backend.Entity.PostLike;
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
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    @Modifying
    @Query("DELETE FROM PostLike post WHERE post.postDetail.post_id  = :post_id AND post.user.user_id = :user_id ")
    void deleteLikeByPost_idAndUser_id(@Param("post_id") Integer post_id, @Param("user_id") Integer user_id);

    @Modifying
    @Query("DELETE FROM PostLike post WHERE post.postDetail.post_id  = :post_id ")
    void deleteLikeByPost_id(@Param("post_id") Integer post_id);
    @Modifying
    @Query("DELETE FROM PostLike post WHERE post.user.user_id  = :user_id ")
    void deleteLikeByUser_id(@Param("user_id") Integer user_id);
    @Query("SELECT post FROM PostLike post WHERE post.postDetail.post_id  = :post_id AND post.user.user_id = :user_id ")
    PostLike findLikeByPost_idAndUser_id(@Param("post_id") Integer post_id, @Param("user_id") Integer user_id);
    @Query("SELECT post FROM PostLike post WHERE post.postDetail.post_id  = :post_id")
    List<PostLike> findLikeByPost_id(@Param("post_id") Integer post_id);
}
