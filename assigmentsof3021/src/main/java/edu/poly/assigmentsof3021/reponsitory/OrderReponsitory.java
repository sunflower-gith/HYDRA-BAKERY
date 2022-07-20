package edu.poly.assigmentsof3021.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.assigmentsof3021.domain.Order;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.customer.customerId = ?1")
	Page<Order> findByCustomerId(Long customerId, Pageable pageable);
}
