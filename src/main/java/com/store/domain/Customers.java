package com.store.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Customers\"", schema = "Public")
public class Customers implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Customers\"", allocationSize=1)
	@Column(name="\"Id\"", unique=true, nullable=false)         
	private Long id;  
	
	@Id //Primary temp?
	@Column(name="\"Customer_Id\"")
	private Long customerId;
	
	@Column(name="\"First_Name\"")
	private String firstName;
	
	@Column(name="\"Last_Name\"")
	private String lastName;
	
	@Column(name="\"Phone\"")
	private String phone;
	
	@Column(name="\"Email\"")
	private String email;
	
	@Column(name="\"Street\"")
	private String street;
	
	@Column(name="\"Zip\"")
	private Long zip;
	
	
	//Customers(Customer_Id) --> Orders(Customer_Id)
	@OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
	private List<Orders> ordersList = Lists.newArrayList();
	
	
	
}
