package com.store.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.domain.Customers;
import com.store.domain.OrderItems;
import com.store.domain.Orders;
import com.store.domain.Stores;
import com.store.dto.CustomersDTO;
import com.store.dto.OrderItemsDTO;
import com.store.dto.OrdersDTO;
import com.store.dto.StoresDTO;
import com.store.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	
	
	//1. Retrieve ALL Tables
	public List<CustomersDTO> findAll(){
		
		List<Customers> customerList = customerRepo.findAll();
		System.out.println("Find All...");
		List<CustomersDTO> customerListDTO = customerList.stream().map(toCustomersDTO).collect(Collectors.toList());
		return customerListDTO;
	}
	
	
	//Customers Table
	private Function<Customers, CustomersDTO> toCustomersDTO = new Function<Customers, CustomersDTO>(){
		public CustomersDTO apply(Customers customer) {
			CustomersDTO customerDTO = new CustomersDTO();
				try {
					System.out.println("Getting Customers List....");
					System.out.println(" Data: "+customer.getEmail());
					customerDTO.setId(customer.getId());
////					customerDTO.setCustomerId(customer.getCustomerId());
					customerDTO.setFirstName(customer.getFirstName());
					customerDTO.setLastName(customer.getLastName());
					customerDTO.setPhone(customer.getPhone());
					customerDTO.setEmail(customer.getEmail());
					customerDTO.setStreet(customer.getStreet());
					customerDTO.setZip(customer.getZip());
					//----------------------------------------------------------//
					System.out.println(" Order List: "+customer.getOrdersList());
//					customerDTO.setOrders(customer.getOrdersList().stream()
//												  .map(toOrdersDTO).collect(Collectors.toList()));
					
				}catch(Exception e) {
					log.error("function error toCustomersDTO()", e);
				}
				return customerDTO;
		}
	};
	
	
	//Orders Table
	private Function<Orders, OrdersDTO> toOrdersDTO = new Function<Orders, OrdersDTO>(){

		@Override
		public OrdersDTO apply(Orders orders) {
			System.out.println("Getting Order List...");
			OrdersDTO ordersDTO = new OrdersDTO();
			if(orders != null) {
				ordersDTO.setId(orders.getId());
//				ordersDTO.setOrderId(orders.getOrderId());
				ordersDTO.setOrderStatus(orders.getOrderStatus());
				ordersDTO.setOrderDate(orders.getOrderDate());
//				ordersDTO.setStoreId(toStoresDTO.apply(orders.getStores()));
//				ordersDTO.setCustomerId(orders.getCustomerId());
			}
			return ordersDTO;
		}
		
	};
	
	
	//Order Items Table
//	private Function<OrderItems, OrderItemsDTO> toOrderItemsDTO = new Function<OrderItems, OrderItemsDTO>(){
//
//		@Override
//		public OrderItemsDTO apply(OrderItems orderItems) {
//			System.out.println("Getting Order Items List...");
//			OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
//			if(orderItems != null) {
//				orderItemsDTO.setId(orderItems.getId());
//				orderItemsDTO.setOrdersItemsId(orderItems.getOrdersItemsId());
//				orderItemsDTO.setQuantity(orderItems.getQuantity());
//				orderItemsDTO.setPrice(orderItems.getPrice());
//				orderItemsDTO.setOrder_Id(orderItems.getOrder_Id());
//			}
//			return orderItemsDTO;
//		}
//		
//	};
//	
//	
//	//Stores Table
//	private Function<Stores, StoresDTO> toStoresDTO = new Function<Stores, StoresDTO>(){
//
//		@Override
//		public StoresDTO apply(Stores stores) {
//			System.out.println("Getting Stores List...");
//			StoresDTO storesDTO = new StoresDTO();
//			if(stores != null) {
//				storesDTO.setId(stores.getId());
//				storesDTO.setStoreId(stores.getStoreId());
//				storesDTO.setStoreName(stores.getStoreName());
//				storesDTO.setPhone(stores.getPhone());
//				storesDTO.setEmail(stores.getEmail());
//				storesDTO.setStreet(stores.getStreet());
//				storesDTO.setZip(stores.getZip());
//			}
//			return storesDTO;
//		}
//
//		
//	};
	
	

}
