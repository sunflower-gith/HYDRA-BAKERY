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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@Column(columnDefinition = "nvarchar(100) not null")
	private String name;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private double unitPrice;
	@Column(columnDefinition = "nvarchar(200) not null")
	private String image;
	@Column(columnDefinition = "nvarchar(300) not null")
	private String description;
	@Column(nullable = false)
	private double discount;
	@Temporal(TemporalType.DATE)
	private Date enteredDate = new Date();
	@Column(nullable = false)
	private short status;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
}
