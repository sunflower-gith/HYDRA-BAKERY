package edu.poly.assigmentsof3021.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
@Id
@Column(columnDefinition = "nvarchar(100)")
private String username;
@Column(columnDefinition = "nvarchar(100) not null")
private String password;
}
