package edu.poly.assigmentsof3021.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	private Long customerId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private Date registeredDate = new Date();
	private short status;
	private Boolean isEdit = false;
}
