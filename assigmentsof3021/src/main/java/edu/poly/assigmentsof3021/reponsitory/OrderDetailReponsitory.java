package edu.poly.assigmentsof3021.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.assigmentsof3021.domain.OrderDetail;

@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetail, Long>{
	@Query("SELECT o FROM OrderDetail o WHERE o.order.orderId = ?1")
	Page<OrderDetail> findByOrderId(Long orderId, Pageable pageable);
}
