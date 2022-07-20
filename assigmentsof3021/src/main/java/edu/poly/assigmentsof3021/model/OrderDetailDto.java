package edu.poly.assigmentsof3021.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
	private Long orderDetailId;
	private int quantity;
	private double unitPrice;
	private Long  orderId;
	private Long productId;
}
