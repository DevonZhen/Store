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
	@Column(name="\"Order_Items_Id\"")
	private Long orderItemsId;
	
	@Column(name="\"Item\"")
	private String item;
	
	@Column(name="\"Quantity\"")
	private Long quantity;
	
	@Column(name="\"Price\"")
	private Double price;
	
	@Column(name="\"Order_Id\"")
	private Long order_Id; 
	
	//Order Items ---> Orders
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"Order_Id\"",insertable=false, updatable=false)
	private Orders orders;

	
	
//	@Override
//	 public String toString() {
//	        return "Orders ToString: "+getId()+ " "+getOrderItemsId()+" "+getItem()+" "+getQuantity()+" "
//	        						  +getPrice()+" " +getOrder_Id();
//	    }
	
	
	
}
