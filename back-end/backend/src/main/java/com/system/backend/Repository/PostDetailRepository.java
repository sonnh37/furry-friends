package com.system.backend.Repository;

import com.system.backend.Entity.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Integer> {

}
