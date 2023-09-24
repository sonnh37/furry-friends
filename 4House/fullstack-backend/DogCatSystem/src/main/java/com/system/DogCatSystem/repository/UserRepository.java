package com.system.DogCatSystem.repository;

import com.system.DogCatSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
