package com.store.dto;
import lombok.Data;
@Data
public class OrderItemsDTO {
	private Long orderItemsId;
	private String item;
	private Long quantity;
	private Double price;
	private Long orderId; 
}
