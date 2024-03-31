package com.system.backend.Repository;

import com.system.backend.Entity.Img_Pet;
import com.system.backend.Entity.Img_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@EnableJpaRepositories
@Transactional
public interface ImgPetRepository extends JpaRepository<Img_Pet,Integer> {
    @Query("SELECT img FROM Img_Pet img WHERE img.pet.pet_id = :pet_id")
    List<Img_Pet> findImg_PetByPet_id(@Param("pet_id") Integer pet_id);
    @Modifying
    @Query("DELETE FROM Img_Pet img WHERE img.pet.pet_id = :pet_id")
    void deleteAllByPet_id(@Param("pet_id") Integer pet_id);
}
