package edu.poly.assigmentsof3021.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteLoginDto {
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	private Boolean isEdit = false;
	private Boolean rememberMe = false;
}
