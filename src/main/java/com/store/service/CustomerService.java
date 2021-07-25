package com.store.service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
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
import com.store.repository.OrderItemsRepository;
import com.store.repository.OrdersRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	//1. Retrieve ALL Tables
	public List<CustomersDTO> findAll(){
		List<Customers> customerList = customerRepo.findAll();
		System.out.println("Customers Find All...");
		List<CustomersDTO> customerListDTO = customerList.stream().map(toCustomersDTO).collect(Collectors.toList());
		return customerListDTO;
	}
	
	
	//Insert Customer
//	public CustomersDTO newCustomer(CustomersDTO customerDTO) {
//		Customers customer = newCustomerDomain.apply(customerDTO);
//		customerRepo.save(customer);
//		return customerDTO;
//	}	

		
	
	//Update Customer
	public CustomersDTO customerUpdate(CustomersDTO customersDTO) {
		//Get Customer's Id
		System.out.println("Entering New Zone "+customersDTO.getCustomerId());
		
		//Customers customers = customerRepo.findCustomerId(customersDTO.getCustomerId());
		Optional<Customers> customers = customerRepo.findById(customersDTO.getCustomerId());
		System.out.println("### CustomerId ### = "+customers.get().getCustomerId());
		
		if(customers==null)
			throw new RuntimeException(String.format("Cannot find order with id '%d'",customers.get().getCustomerId()));

			
		customersDomain.accept(customersDTO, customers.get());
		System.out.println("Hello World");
//		return null;
		return customersDTO;
	}	

			
	
	//Delete Customer + Orders
//	public void deleteCustomer(Long id) {
//		customerRepo.deleteById(id);
////		ordersRepo.deleteByCustomerId(id);
////		orderItemsRepo.deleteByOrderId(id);
//	}
	
	

	
	
	//==================================================================================================================//
	
	
	//Customers Table
	private Function<Customers, CustomersDTO> toCustomersDTO = new Function<Customers, CustomersDTO>(){
		public CustomersDTO apply(Customers customer) {
			System.out.println("Getting Customers List....");
			CustomersDTO customerDTO = new CustomersDTO();
				try {
					customerDTO.setCustomerId(customer.getCustomerId());
					customerDTO.setFirstName(customer.getFirstName());
					customerDTO.setLastName(customer.getLastName());
					customerDTO.setPhone(customer.getPhone());
					customerDTO.setEmail(customer.getEmail());
					customerDTO.setStreet(customer.getStreet());
					customerDTO.setZip(customer.getZip());
					
//					System.out.println(" Order List: "+customer.getOrdersList());
					customerDTO.setOrders(customer.getOrdersList().stream().map(toOrdersDTO).collect(Collectors.toList()));
					
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
				ordersDTO.setOrderId(orders.getOrderId());
				ordersDTO.setOrderStatus(orders.getOrderStatus());
				ordersDTO.setOrderDate(orders.getOrderDate());
				ordersDTO.setStoreId(orders.getStoreId());
//				ordersDTO.setStores(toStoresDTO.apply(orders.getStores()));
//				ordersDTO.setCustomerId(orders.getCustomerId());
//				ordersDTO.setOrderItems(orders.getOrderItemsList().stream().map(toOrderItemsDTO).collect(Collectors.toList())); 
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
				orderItemsDTO.setOrderItemsId(orderItems.getOrderItemsId());
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

	//==================================================================================================================//
	//Update Customer
	
	BiConsumer<CustomersDTO, Customers> customersDomain = new BiConsumer<CustomersDTO, Customers>(){
		@Override
		public void accept(CustomersDTO customersDTO, Customers customers) {
			customers.setCustomerId(customersDTO.getCustomerId());
			customers.setFirstName(customersDTO.getFirstName());
			customers.setLastName(customersDTO.getLastName());
			customers.setPhone(customersDTO.getPhone());
			customers.setEmail(customersDTO.getEmail());
			customers.setStreet(customersDTO.getStreet());
			customers.setZip(customersDTO.getZip());
			
			//Orders Array + Order Items Array
			System.out.println("Clearing...");
			
			
			//Order Items List Clear
//			for(Orders o : customers.getOrdersList()) {
//				orderItemsRepo.deleteInBatch(o.getOrderItemsList());
//  			    o.getOrderItemsList().clear();
//  			    orderItemsRepo.flush();
//			}
					
			//Orders List Clear
			ordersRepo.deleteInBatch(customers.getOrdersList());
			customers.getOrdersList().clear();
//			ordersRepo.flush();
			
			//Adding Orders
			//(Not Working)
			System.out.println("Re-adding...");
			if(!customersDTO.getOrders().isEmpty()) {
				for(OrdersDTO ordersDTO : customersDTO.getOrders()) {
					Orders orders = toNewOrdersDomain.apply(ordersDTO);
					System.out.println("Customer ---- "+customers);
					System.out.println("Orders ---- "+orders);
					orders.setCustomers(customers);
					customers.getOrdersList().add(orders);
				}
			}
			
			//Adding Order Items
//			for(Orders o: customers.getOrdersList()) {
//				if(!o.getOrderItemsList().isEmpty()) {
//					for(OrderItemsDTO orderItemsDTO : o.getOrderItemsList()) {
//						
//					}
//				}
//			}
//			
		}
	};
	
	
	
	
	//==================================================================================================================//
	//Update Order + OrderItems?
	
	BiConsumer<OrdersDTO, Orders> ordersDomain = new BiConsumer<OrdersDTO, Orders>() {
		@Override
		public void accept(OrdersDTO ordersDTO, Orders orders) {
			orders.setOrderId(ordersDTO.getOrderId());
			orders.setOrderStatus(ordersDTO.getOrderStatus());
			orders.setOrderDate(ordersDTO.getOrderDate());
//			orders.setStoreId(ordersDTO.getStoreId());
//			orders.setCustomerId(ordersDTO.getCustomerId());
			
			//Reset current Order Items List
//			orderItemsRepo.deleteInBatch(orders.getOrderItemsList());
//			orders.getOrderItemsList().clear();
			
			//Add the new order items list
			if(!ordersDTO.getOrderItems().isEmpty()) {
				for(OrderItemsDTO orderItemsDTO : ordersDTO.getOrderItems()) {
					OrderItems orderItems = toNewOrderItemsDomain.apply(orderItemsDTO);
//					orderItems.setOrders(orders);
//					orders.getOrderItemsList().add(orderItems);
				}
			}		
		}
	};
	
	
	Function<OrdersDTO, Orders> toNewOrdersDomain = new Function<OrdersDTO, Orders>(){
		@Override
		public Orders apply(OrdersDTO ordersDTO) {
			Orders orders = new Orders();
			orders.setOrderId(ordersDTO.getOrderId());
			orders.setOrderStatus(ordersDTO.getOrderStatus());
			orders.setOrderDate(ordersDTO.getOrderDate());
			orders.setStoreId(ordersDTO.getStoreId());
//			orders.setCustomers(ordersDTO.getC);
			return orders;
		}
	};
	
	
	
	
	Function<OrderItemsDTO, OrderItems> toNewOrderItemsDomain = new Function<OrderItemsDTO, OrderItems>(){
		@Override
		public OrderItems apply(OrderItemsDTO orderItemsDTO) {
			OrderItems orderItems = new OrderItems();
			orderItems.setOrderItemsId(orderItemsDTO.getOrderItemsId());
			orderItems.setItem(orderItemsDTO.getItem());
			orderItems.setQuantity(orderItemsDTO.getQuantity());
			orderItems.setPrice(orderItemsDTO.getPrice());
			orderItems.setOrder_Id(orderItemsDTO.getOrderId());
			//Returns the new Order Items List
			return orderItems;
		}
	};
	
	//==================================================================================================================//
	//Insert Customer
	
	Function<CustomersDTO, Customers> newCustomerDomain = new Function<CustomersDTO, Customers>(){
		@Override
		public Customers apply(CustomersDTO customersDTO){
			Customers customer = new Customers();
			customer.setCustomerId(customersDTO.getCustomerId());
			customer.setFirstName(customersDTO.getFirstName());
			customer.setLastName(customersDTO.getLastName());
			customer.setPhone(customersDTO.getPhone());
			customer.setEmail(customersDTO.getEmail());
			customer.setStreet(customersDTO.getStreet());
			customer.setZip(customersDTO.getZip());
	
			return customer;
		}
	};
	
	

	//Insert Stores
	Function<StoresDTO, Stores> toNewStoresDomain = new Function<StoresDTO, Stores>(){
		@Override
		public Stores apply(StoresDTO storesDTO){
			Stores stores = new Stores();
			stores.setStoreName(storesDTO.getStoreName());
			stores.setPhone(storesDTO.getPhone());
			stores.setEmail(storesDTO.getEmail());
			stores.setStreet(storesDTO.getStreet());
			stores.setZip(storesDTO.getZip());
			return stores;
		}
	};
 
	 
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	public CustomersDTO test1(Long id) {
		//Get Customer's Id
		System.out.println("Entering New Zone "+ id);
		try {
			//Customers customers = customerRepo.findCustomerId(customersDTO.getCustomerId());
			Optional<Customers> customers = customerRepo.findById(id);
			System.out.println("### CustomerId ### = "+customers.get().getFirstName());
//			if(customers==null)
//				throw new RuntimeException(String.format("Cannot find order with id '%d'",customers));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
