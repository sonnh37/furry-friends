package com.system.backend.Repository;

import com.system.backend.Enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.role r WHERE r.roleId = :roleId")
    List<User> findByRoleId(@Param("roleId") int roleId);
}
