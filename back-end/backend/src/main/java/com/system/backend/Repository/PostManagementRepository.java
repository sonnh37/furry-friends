package com.system.backend.Repository;

import com.system.backend.Entity.PostManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostManagementRepository extends JpaRepository<PostManagement, Integer> {
}
