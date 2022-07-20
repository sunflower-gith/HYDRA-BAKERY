package edu.poly.assigmentsof3021.reponsitory;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.assigmentsof3021.domain.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Page<Category> findByNameContaining(String name, Pageable pageable);
	@Query("SELECT c FROM Category c WHERE c.CategoryId = ?1")
	Page<Category> findAllById(Long categoryId, Pageable pageable);
}
