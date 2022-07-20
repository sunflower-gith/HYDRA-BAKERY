package edu.poly.assigmentsof3021.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Long orderId;
	private Date orderDate;
	private double amount;
	private short status;
	private Long customerId;
	private Boolean isEdit = false;
}
