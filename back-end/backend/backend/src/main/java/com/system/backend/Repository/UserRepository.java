package com.system.backend.Repository;

import com.system.backend.Entity.Role;
import com.system.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByAccountAndPassword(String account, String password);
    User findUserByAccount(String account);
    @Query("SELECT r FROM Role r WHERE r.role_id = :role_id")
    Role findByRoleId(@Param("role_id") Integer role_id);
    @Query("SELECT r FROM User r WHERE r.user_id = :user_id")
    User findByUser_id(@Param("user_id")Integer user_id);









}
