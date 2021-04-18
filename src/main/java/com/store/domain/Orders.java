package com.store.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Orders\"", schema = "Public")
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Orders\"", allocationSize=1)
	@Column(name="\"Id\"", unique=true, nullable=false) 
	private Long id;
	
//	@Column(name="\"Order_Id\"")
//	private Long orderId;
	
	@Column(name="\"Order_Status\"")
	private String orderStatus;
	
	@Column(name="\"Order_Date\"")
	private Date orderDate;
	
	@Column(name="\"Store_Id\"")
	private Long storeId; 
	
//	@Column(name="\"Customer_Id\"")
//	private Long customerId; 
	
	//Orders --> Customers Relationship
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Customer_Id\"", nullable = false)
	private Customers customerId;
	
	//Orders(PK: Order_Id) --> Order Items(FK: Order_Id)
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "\"Order_Id\"",insertable=false, updatable=false)
//	private OrderItems orderItems;
	
	//Orders(PK: Store_Id) --> Store(FK: Store_Id)
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"Store_Id\"",insertable=false, updatable=false)
	private Stores stores;
	
}
