package com.simran.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Data
@NoArgsConstructor
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
//	E_ID, E_Name, PassKey, Designation, Contact, Address, Salary
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
//
//	@Column(name = "last_name")
//	private String lastName;
	
	private String email;
	
	private String password;

	private String designation;

	private String contact;

	private String address;

	private Integer salary;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role> roles;



	public User(String name, String email, String password, Collection<Role> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(Integer id, String name, String email, String password, String designation, String contact, String address, Integer salary, Collection<Role> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.designation = designation;
		this.contact = contact;
		this.address = address;
		this.salary = salary;
		this.roles = roles;
	}
}
