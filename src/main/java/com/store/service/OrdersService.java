package com.store.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.domain.OrderItems;
import com.store.domain.Orders;
import com.store.domain.Stores;
import com.store.dto.OrderItemsDTO;
import com.store.dto.OrdersDTO;
import com.store.dto.StoresDTO;
import com.store.repository.OrdersRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	
	public List<OrdersDTO> findAll(){
		List<Orders> orderList = ordersRepo.findAll();
		System.out.println("Orders Find All..");
		List<OrdersDTO> orderListDTO = orderList.stream().map(toOrdersDTO).collect(Collectors.toList());
		return orderListDTO;
		
	}
	
	private Function<Orders, OrdersDTO> toOrdersDTO = new Function<Orders, OrdersDTO>(){
		public OrdersDTO apply(Orders orders) {
			System.out.println("Getting Orders List...");
			OrdersDTO ordersDTO = new OrdersDTO();
			try {
				ordersDTO.setId(orders.getId());
				ordersDTO.setOrderId(orders.getOrderId());
				ordersDTO.setOrderStatus(orders.getOrderStatus());
				ordersDTO.setOrderDate(orders.getOrderDate());
				ordersDTO.setStore(toStoresDTO.apply(orders.getStores()));
				ordersDTO.setCustomerId(orders.getCustomerId());
//				ordersDTO.setOrderItems(toOrderItemsDTO.apply(orders.getOrderItems()));
				ordersDTO.setOrderItems(orders.getOrderItemsList().stream().map(toOrderItemsDTO).collect(Collectors.toList()));
						 
			}catch(Exception e) {
				log.error("function error toOrderDTO()", e);
			}		
			return ordersDTO;
		}
		
	};
	
	
	
	//Order Items Table
	private Function<OrderItems, OrderItemsDTO> toOrderItemsDTO = new Function<OrderItems, OrderItemsDTO>(){

		@Override
		public OrderItemsDTO apply(OrderItems orderItems) {
			System.out.println("Getting Order Items List...");
			OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
			if(orderItems != null) {
				orderItemsDTO.setId(orderItems.getId());
				orderItemsDTO.setOrdersItemsId(orderItems.getOrdersItemsId());
				orderItemsDTO.setItem(orderItems.getItem());
				orderItemsDTO.setQuantity(orderItems.getQuantity());
				orderItemsDTO.setPrice(orderItems.getPrice());
				orderItemsDTO.setOrderId(orderItems.getOrder_Id());
			}
			return orderItemsDTO;
		}
		
	};
	
	
	
	
	//Stores Table
	private Function<Stores, StoresDTO> toStoresDTO = new Function<Stores, StoresDTO>(){

		@Override
		public StoresDTO apply(Stores stores) {
			System.out.println("Getting Stores List...");
			StoresDTO storesDTO = new StoresDTO();
			if(stores != null) {
				storesDTO.setId(stores.getId());
				storesDTO.setStoreName(stores.getStoreName());
				storesDTO.setPhone(stores.getPhone());
				storesDTO.setEmail(stores.getEmail());
				storesDTO.setStreet(stores.getStreet());
				storesDTO.setZip(stores.getZip());
			}
			return storesDTO;
		}
	};


}
