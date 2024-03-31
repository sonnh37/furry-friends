package com.system.backend.Repository;

import com.system.backend.Entity.Img_Product;
import com.system.backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@EnableJpaRepositories
@Transactional
@Repository
public interface ImgProductRepository extends JpaRepository<Img_Product, Integer> {
    @Query("SELECT img FROM Img_Product img WHERE img.product.product_id = :product_id")
    List<Img_Product> findImg_ProductByProduct_id(@Param("product_id") Integer product_id);
    @Modifying
    @Query("DELETE FROM Img_Product img WHERE img.product.product_id = :product_id")
    void deleteAllByProduct_id(@Param("product_id") Integer product_id);
}
