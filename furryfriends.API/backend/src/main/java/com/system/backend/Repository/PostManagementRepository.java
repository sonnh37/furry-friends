package com.system.backend.Repository;

import com.system.backend.Entity.PostManagement;
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
public interface PostManagementRepository extends JpaRepository<PostManagement, Integer> {

    @Modifying
    @Query("DELETE FROM PostManagement postmn WHERE postmn.postDetail.post_id  = :post_id")
    void deletePostManagementByPost_id(@Param("post_id") Integer post_id);

    @Modifying
    @Query("DELETE FROM PostManagement postmn WHERE postmn.user.user_id = :user_id")
    void deletePostManagementByUser_id(@Param("user_id") Integer user_id);

    @Query("SELECT postmn FROM PostManagement postmn WHERE postmn.user.user_id  = :user_id")
    List<PostManagement> findPostManagementByPost_id(@Param("user_id") Integer user_id);
}
