package com.system.backend.Repository;

import com.system.backend.Enity.Role;
import com.system.backend.Enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    @Query("SELECT r FROM Role r WHERE r.roleID = :role_id")
    Role findByRoleId(@Param("role_id") int role_id);
    User findByUserID(Integer userId);


}
