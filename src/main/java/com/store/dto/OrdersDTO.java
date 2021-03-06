package com.store.dto;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.store.domain.Customers;
import com.store.util.CustomDateDeserializer;
import com.store.util.CustomDateSerializer;

import lombok.Data;

@Data
public class OrdersDTO {
	private Long orderId;
	private String orderStatus;
	@JsonDeserialize(using = CustomDateDeserializer.class)     // java.util.Date object with JSON format
	@JsonSerialize(using   = CustomDateSerializer.class)
	private Date orderDate;
//	private StoresDTO stores;  // 1 - 1 Stores
	private Long storeId;
//	private Long customers; 
	//---------------------------------//
	private List<OrderItemsDTO> orderItems = Lists.newArrayList();  //1 - Many Order Items
}
