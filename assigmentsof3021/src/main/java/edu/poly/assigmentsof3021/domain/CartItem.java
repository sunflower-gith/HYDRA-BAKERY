package edu.poly.assigmentsof3021.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	private Long productId;
	private String name;
	private int quantity;
	private double unitPrice;
	private String image;
//	private MultipartFile imageFile;
}
