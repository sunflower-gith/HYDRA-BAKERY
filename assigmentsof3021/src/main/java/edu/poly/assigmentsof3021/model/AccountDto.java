package edu.poly.assigmentsof3021.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	@NotEmpty
	@Length(min = 5)
	private String username;
	@NotEmpty
	@Length(min = 3)
	private String password;
	private Boolean isEdit = false;
}
