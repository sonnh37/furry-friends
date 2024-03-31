package com.system.backend.Repository;

import com.system.backend.Entity.PostComment;
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
@Repository
@Transactional
public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
    @Modifying
    @Query("DELETE FROM PostComment post WHERE post.postDetail.post_id  = :post_id AND post.user.user_id = :user_id ")
    void deleteCommentByPost_idAndUser_id(@Param("post_id") Integer post_id, @Param("user_id") Integer user_id);
    @Modifying
    @Query("DELETE FROM PostComment post WHERE post.commentID = :comment_id")
    void deleteCommentByComment_id(@Param("comment_id") Integer comment_id);
    @Modifying
    @Query("DELETE FROM PostComment post WHERE post.postDetail.post_id  = :post_id ")
    void deleteCommentByPost_id(@Param("post_id") Integer post_id);
    @Modifying
    @Query("DELETE FROM PostComment post WHERE post.user.user_id  = :user_id ")
    void deleteCommentByUser_id(@Param("user_id") Integer user_id);

    @Query("SELECT post FROM PostComment post WHERE post.postDetail.post_id  = :post_id AND post.user.user_id = :user_id ")
    PostLike findCommentByPost_idAndUser_id(@Param("post_id") Integer post_id, @Param("user_id") Integer user_id);
    @Query("SELECT post FROM PostComment post WHERE post.postDetail.post_id  = :post_id")
    List<PostComment> findCommentByPost_id(@Param("post_id") Integer post_id);
}
