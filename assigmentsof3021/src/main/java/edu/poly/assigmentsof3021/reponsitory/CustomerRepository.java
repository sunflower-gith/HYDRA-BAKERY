package edu.poly.assigmentsof3021.reponsitory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.assigmentsof3021.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Page<Customer> findByNameContaining(String name, Pageable pageable);

	@Query("SELECT c FROM Customer c WHERE c.email LIKE ?1")
	Optional<Customer> findByEmail(String email);
	
}
