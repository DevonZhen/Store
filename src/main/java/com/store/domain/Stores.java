package com.store.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Stores\"", schema = "Public")
public class Stores implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Stores\"", allocationSize=1)
	@Column(name="\"Id\"", unique=true, nullable=false) 
	private Long id;

	@Column(name="\"Store_Id\"")
	private Long storeId;

	@Column(name="\"Store_Name\"")
	private String storeName;

	@Column(name="\"Phone\"")
	private String phone;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"Street\"")
	private String street;

	@Column(name="\"Zip\"")
	private Long zip;
	
	//Stores --> Orders
	@OneToOne(mappedBy = "stores")
	private Orders orders;
	
	
}
