package com.store.dto;
import lombok.Data;
@Data
public class OrderItemsDTO {
	private Long id;
	private Long ordersItemsId;
	private String item;
	private Long quantity;
	private Double price;
	private Long Order_Id; 
}
