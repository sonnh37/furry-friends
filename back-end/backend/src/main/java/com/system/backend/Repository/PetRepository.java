package com.system.backend.Repository;

import com.system.backend.Entity.Pet;
import com.system.backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EnableJpaRepositories
@Transactional
public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query("SELECT p FROM Pet p WHERE p.pet_id = :pet_id")
    Pet findPetByPet_id(@Param("pet_id") Integer pet_id);
    @Query("SELECT p FROM Pet p WHERE p.user.user_id = :user_id")
    List<Pet> findPetsByUser_id(@Param("user_id") Integer user_id);
    @Query("SELECT p FROM Pet p WHERE UPPER(p.title) LIKE CONCAT('%',:title,'%') ORDER BY p.pet_id DESC")
    List<Pet> findPetsByTitle(@Param("title") String title);
    @Query("SELECT p FROM Pet p WHERE p.type = :type")
    List<Pet> findPetsByType(@Param("type") String type);
    @Query("SELECT p FROM Pet p ORDER BY p.pet_id DESC")
    List<Pet> findAll();
    @Query("SELECT p FROM Pet p WHERE p.user.user_id = :user_id AND p.pet_id = :pet_id")
    Pet findPetsByUser_idAndPet_id(@Param("pet_id") Integer pet_id,
                                               @Param("user_id") Integer user_id);

}
