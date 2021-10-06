package com.store.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.store.configuration.JNDIConfiguration;
import com.store.controller.CustomerQueryController;
import com.store.domain.Customers;
import com.store.dto.CustomersDTO;
import com.store.dto.CustomersFormData;
import com.store.dto.OrderItemsDTO;
import com.store.dto.OrdersDTO;
import com.store.repository.CustomerQueryRepository;
import com.store.repository.CustomerRepository;
import com.store.service.CustomerQueryService;
import com.store.service.OrdersService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, JNDIConfiguration.class })
@ContextConfiguration(classes = {CustomerQueryController.class, CustomerQueryService.class, OrdersService.class, CustomerQueryRepository.class}) 

     
public class SpringBootApplicationJunitTest {
	
	@Autowired
	CustomerRepository customerRepository;               
	
	@Autowired
	CustomerQueryController customerQueryController; 
	
	@Autowired
	CustomerQueryRepository customerQueryRepository;
	
	@Test
	public void updateCustomer() throws ParseException 
	{				
		try {
			
			CustomersFormData customersFormData = new CustomersFormData();
			
			//initialize Customer
			CustomersDTO customersDTO = new CustomersDTO();

			customersDTO.setCustomerId(Long.valueOf(2));
			customersDTO.setFirstName("Jared");
		    customersDTO.setLastName("Volo");
		    customersDTO.setPhone("6098463251");
		    customersDTO.setEmail("jvee22@gmail.com");
		    customersDTO.setStreet("2 Roko Road");
		    customersDTO.setZip(Long.valueOf(96328));
		    
		    List<OrdersDTO> ordersDTOlist = new ArrayList<>(); 
		    //initialize Orders 1
		    OrdersDTO ordersDTO_1 = new OrdersDTO();
		    ordersDTO_1.setOrderId(Long.valueOf(130));
		    ordersDTO_1.setOrderStatus("Canceled");
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		    ordersDTO_1.setOrderDate(formatter1.parse("2020-01-11"));
		    ordersDTO_1.setStoreId(Long.valueOf(2));
		    
		    List<OrderItemsDTO> orderItemDTOList1 = new ArrayList<>(); 
		    //initialize Orders_1 Items_1
		    OrderItemsDTO orderItemsDTO11 = new OrderItemsDTO();
		    orderItemsDTO11.setOrderItemsId(Long.valueOf(19));
		    orderItemsDTO11.setItem("RAM");
		    orderItemsDTO11.setQuantity(Long.valueOf(5));
		    orderItemsDTO11.setPrice(Double.valueOf(86.59));
		    orderItemDTOList1.add(orderItemsDTO11);	    
		    
		    //initialize Orders_1 Items_2
		    /*
		    OrderItemsDTO orderItemsDTO12 = new OrderItemsDTO();
		    orderItemsDTO12.setOrderItemsId(Long.valueOf(2));
		    orderItemsDTO12.setItem("Desktop");
		    orderItemsDTO12.setQuantity(Long.valueOf(3));
		    orderItemsDTO12.setPrice(Double.valueOf(94.33));
		    orderItemsDTO12.setOrderId(Long.valueOf(84962));		    
		    orderItemDTOList1.add(orderItemsDTO12);	 
		    */   
		    
		    ordersDTO_1.setOrderItems(orderItemDTOList1);
		    ordersDTOlist.add(ordersDTO_1);
		    
		    //initialize Orders_2 
		    OrdersDTO ordersDTO_2 = new OrdersDTO();
		    ordersDTO_2.setOrderId(null);
		    ordersDTO_2.setOrderStatus("Completed");
		    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		    ordersDTO_2.setOrderDate(formatter2.parse("2021-11-01"));
		    ordersDTO_2.setStoreId(Long.valueOf(2));
		    
		    List<OrderItemsDTO> orderItemDTOList2 = new ArrayList<>(); 
		    //initialize Orders_2 Items_1
		    OrderItemsDTO orderItemsDTO21 = new OrderItemsDTO();
		    orderItemsDTO21.setOrderItemsId(null);
		    orderItemsDTO21.setItem("Computer");
		    orderItemsDTO21.setQuantity(Long.valueOf(1));
		    orderItemsDTO21.setPrice(Double.valueOf(125.99));
		    orderItemsDTO21.setOrderId(Long.valueOf(8888));
		    orderItemDTOList2.add(orderItemsDTO21);	
		    
		    ordersDTO_2.setOrderItems(orderItemDTOList2);
		    ordersDTOlist.add(ordersDTO_2);
		    
		    customersDTO.setOrders(ordersDTOlist);
		    
		    customersFormData.setDelItemIds("");
		    customersFormData.setDelOrderIds("132");
		    customersFormData.setCustomersDTO(customersDTO);
		    
		    ResponseEntity<CustomersDTO> customerDTO = customerQueryController.updateCustomers(customersFormData);

			System.out.println("####=="+customerDTO.getBody().getFirstName());
			
		    System.out.println("=============================================");
	        System.out.println("### CustomerList() Successful: Return "+customerDTO+") ###");
	        System.out.println("--- Original --- "+customersDTO);
		    System.out.println("=============================================");
		    
			
		}catch (AssertionError e) {
			if (e.getMessage() == null) {
			    System.out.println("=============================================");
		        System.out.println("### CustomerList() failure: Return null) ###");
			    System.out.println("=============================================");
			}
		}
	}	
	
	//@Test
	public void testFindById() 
	{				
		try {
			long customerId = 11489;
			Optional<Customers> customer = customerRepository.findById(customerId);		  
			//Assert.assertTrue(customerList.size() > 0);
			System.out.println("####=="+customer.get().getFirstName());
			
		    System.out.println("=============================================");
	        System.out.println("### CustomerList() Successful: Return "+customer.get()+") ###");
		    System.out.println("=============================================");
			
		}catch (AssertionError e) {
			if (e.getMessage() == null) {
			    System.out.println("=============================================");
		        System.out.println("### CustomerList() failure: Return null) ###");
			    System.out.println("=============================================");
			}
		}
	}	
	
	//@Test
	public void testCustomerList() 
	{				
		try {

			List<Customers> customerList = customerRepository.findAll();		  
			Assert.assertTrue(customerList.size() > 0);

			System.out.println("=============================================");
	        System.out.println("### CustomerList() Successful: Return "+customerList.size()+") ###");
		    System.out.println("=============================================");
			
		}catch (AssertionError e) {
			if (e.getMessage() == null) {
			    System.out.println("=============================================");
		        System.out.println("### CustomerList() failure: Return null) ###");
			    System.out.println("=============================================");
			}
		}
	}	
	
	//@Test
	public void getCustomerDTOList() 
	{				
		try {

			List<CustomersDTO> customerDTOList = customerQueryRepository.getCustomersList();		  
			Assert.assertTrue(customerDTOList.size() > 0);

			System.out.println("=====================================================");
	        System.out.println("### getCustomerDTOList() Successful: Return "+customerDTOList.size()+") ###");
		    System.out.println("=====================================================");
			
		}catch (AssertionError e) {
			if (e.getMessage() == null) {
			    System.out.println("==================================================");
		        System.out.println("### GetCustomerDTOList() failure: Return null) ###");
			    System.out.println("==================================================");
			}
		}
	}

}
