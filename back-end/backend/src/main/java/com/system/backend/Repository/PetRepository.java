package com.system.backend.Repository;

import com.system.backend.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@EnableJpaRepositories
@Transactional
public interface PetRepository extends JpaRepository<Pet, Integer> {
}
