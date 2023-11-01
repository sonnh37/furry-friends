
package com.system.backend.Repository;

import com.system.backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@EnableJpaRepositories
@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.product_id = :product_id")
    Product findProductByProduct_id(@Param("product_id") Integer product_id);
    @Query("SELECT p FROM Product p WHERE p.user.user_id = :user_id")
    List<Product> findProductsByUser_id(@Param("user_id") Integer user_id);
    @Query("SELECT p FROM Product p WHERE p.user.user_id = :user_id AND p.product_id = :product_id")
    Product findProductsByUser_idAndProduct_id(@Param("product_id") Integer product_id,
                                                     @Param("user_id") Integer user_id);



}
