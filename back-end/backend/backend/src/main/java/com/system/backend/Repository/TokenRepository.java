package com.system.backend.Repository;

import com.system.backend.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("select t from Token t inner join User u on t.user.user_id = u.user_id where (t.expired = false or t.revoked = false) and u.user_id =:user_id"
    )
    List<Token> findAllValidTokenByUser(@Param("user_id")Integer user_id);


    Optional<Token> findByToken(String token);
}
