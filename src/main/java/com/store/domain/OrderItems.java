package com.store.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Order_Items\"", schema = "Public")
public class OrderItems implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Order_Items\"", allocationSize=1)
	@Column(name="\"Id\"", unique=true, nullable=false) 
	private Long id;
	
	@Column(name="\"Order_Items_Id\"")
	private Long ordersItemsId;
	
	@Column(name="\"Item\"")
	private String item;
	
	@Column(name="\"Quantity\"")
	private Long quantity;
	
	@Column(name="\"Price\"")
	private Double price;
	
	@Column(name="\"Order_Id\"")
	private Long orderId; 
	
	//Order Items ---> Orders
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"Order_Id\"",insertable=false, updatable=false)
	private Orders orders;
//	@OneToOne(mappedBy="orderItems")
//	private Orders orders;
	
	
	
	
	
}
