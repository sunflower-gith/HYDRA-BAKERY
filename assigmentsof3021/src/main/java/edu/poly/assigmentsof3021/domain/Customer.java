package edu.poly.assigmentsof3021.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers")
public class Customer implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@Column(columnDefinition = "nvarchar(100) not null")
	private String name;
	@Column(columnDefinition = "varchar(100) not null")
	private String email;
	@Column(columnDefinition = "varchar(100) not null")
	private String password;
	@Column(columnDefinition = "varchar(11) not null")
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date registeredDate = new Date();
	@Column(nullable = false)
	private short status;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;
}
