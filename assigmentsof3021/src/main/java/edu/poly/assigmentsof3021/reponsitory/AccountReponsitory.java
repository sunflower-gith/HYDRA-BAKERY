package edu.poly.assigmentsof3021.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.assigmentsof3021.domain.Account;
@Repository
public interface AccountReponsitory extends JpaRepository<Account, String>{
	@Query("SELECT a FROM Account a WHERE a.username LIKE ?1 AND a.password LIKE ?2")
	Account login(String username, String password);
	
}
