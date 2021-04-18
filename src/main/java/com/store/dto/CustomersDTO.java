package com.store.dto;
import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class CustomersDTO {
	private Long id;
	private Long customerId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String street;
	private Long zip;
	//-----------------------------------//
	private List<OrdersDTO> orders = Lists.newArrayList();  // 1 - Many Customers
	
}
