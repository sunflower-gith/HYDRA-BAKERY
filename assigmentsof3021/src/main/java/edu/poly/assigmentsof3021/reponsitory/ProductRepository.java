package edu.poly.assigmentsof3021.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.assigmentsof3021.domain.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByNameContaining(String name, Pageable pageable);
	@Query("SELECT p FROM Product p WHERE p.category.CategoryId = ?1")
	Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

}
