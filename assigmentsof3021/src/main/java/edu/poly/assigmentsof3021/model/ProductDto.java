package edu.poly.assigmentsof3021.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
private Long productId;
private String name;
private int quantity;
private double unitPrice;
private String image;
private MultipartFile imageFile;
private String description;
private double discount;
private Date enteredDate = new Date();
private short status;
private Long categoryId;
private Boolean isEdit = false;
}
