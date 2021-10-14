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

			customersDTO.setCustomerId(Long.valueOf(4));
			customersDTO.setFirstName("Mako");
		    customersDTO.setLastName("Loan");
		    customersDTO.setPhone("6091239476");
		    customersDTO.setEmail("malo609@gmail.com");
		    customersDTO.setStreet("6 Turken Road");
		    customersDTO.setZip(Long.valueOf(84629));
		    
		    List<OrdersDTO> ordersDTOlist = new ArrayList<>(); 
		    //initialize Orders 1
		    OrdersDTO ordersDTO_1 = new OrdersDTO();
		    ordersDTO_1.setOrderId(Long.valueOf(117));
		    ordersDTO_1.setOrderStatus("Canceled");
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		    ordersDTO_1.setOrderDate(formatter1.parse("2015-3-15"));
		    ordersDTO_1.setStoreId(Long.valueOf(3));
		    
		    List<OrderItemsDTO> orderItemDTOList1 = new ArrayList<>(); 
		    //initialize Orders_1 Items_1
		    OrderItemsDTO orderItemsDTO11 = new OrderItemsDTO();
		    orderItemsDTO11.setOrderItemsId(Long.valueOf(14));
		    orderItemsDTO11.setItem("Phone");
		    orderItemsDTO11.setQuantity(Long.valueOf(1));
		    orderItemsDTO11.setPrice(Double.valueOf(1200.00));
		    orderItemDTOList1.add(orderItemsDTO11);	    
		  //initialize Orders_1 Items_2
		    OrderItemsDTO orderItemsDTO12 = new OrderItemsDTO();
		    orderItemsDTO12.setOrderItemsId(null);
		    orderItemsDTO12.setItem("TestD");
		    orderItemsDTO12.setQuantity(Long.valueOf(333));
		    orderItemsDTO12.setPrice(Double.valueOf(3333.00));
		    orderItemDTOList1.add(orderItemsDTO12);	  
		    
		    ordersDTO_1.setOrderItems(orderItemDTOList1);
		    ordersDTOlist.add(ordersDTO_1);

		    //initialize Orders_2 
		    OrdersDTO ordersDTO_2 = new OrdersDTO();
		    ordersDTO_2.setOrderId(Long.valueOf(7456));
		    ordersDTO_2.setOrderStatus("Completed");
		    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		    ordersDTO_2.setOrderDate(formatter2.parse("2021-10-02"));
		    ordersDTO_2.setStoreId(Long.valueOf(3));
		    
		    List<OrderItemsDTO> orderItemDTOList2 = new ArrayList<>(); 
		    //initialize Orders_2 Items_1
		    OrderItemsDTO orderItemsDTO21 = new OrderItemsDTO();
		    orderItemsDTO21.setOrderItemsId(Long.valueOf(15));
		    orderItemsDTO21.setItem("Case");
		    orderItemsDTO21.setQuantity(Long.valueOf(1));
		    orderItemsDTO21.setPrice(Double.valueOf(500.36));
		    orderItemsDTO21.setOrderId(Long.valueOf(118));
		    orderItemDTOList2.add(orderItemsDTO21);	
		    //initialize Orders_2 Items_2  <-- Order ID YES Order Item ID NO
		    OrderItemsDTO orderItemsDTO22 = new OrderItemsDTO();
		    orderItemsDTO22.setOrderItemsId(null);
		    orderItemsDTO22.setItem("TestC");
		    orderItemsDTO22.setQuantity(Long.valueOf(122));
		    orderItemsDTO22.setPrice(Double.valueOf(84.11));
		    orderItemsDTO22.setOrderId(null);
		    orderItemDTOList2.add(orderItemsDTO22);
		    
		    ordersDTO_2.setOrderItems(orderItemDTOList2);
		    ordersDTOlist.add(ordersDTO_2);
		    
		    //initialize Orders 3 <-- Order ID NO Order Item ID NO
		    OrdersDTO ordersDTO_3 = new OrdersDTO();
		    ordersDTO_3.setOrderId(null);
		    ordersDTO_3.setOrderStatus("Waiting");
		    SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
		    ordersDTO_3.setOrderDate(formatter3.parse("1996-4-16"));
		    ordersDTO_3.setStoreId(Long.valueOf(2));
		    ordersDTOlist.add(ordersDTO_3);	
		    //Order Item 3-1 <-- Order ID NO Order Item ID NO
		    List<OrderItemsDTO> orderItemDTOList3 = new ArrayList<>();
		    OrderItemsDTO orderItemsDTO31 = new OrderItemsDTO();
		    orderItemsDTO31.setOrderItemsId(null);
		    orderItemsDTO31.setItem("TestA");
		    orderItemsDTO31.setQuantity(Long.valueOf(12));
		    orderItemsDTO31.setPrice(Double.valueOf(1.36));
		    orderItemsDTO31.setOrderId(null);
		    orderItemDTOList3.add(orderItemsDTO31);
		    //Order Item 3-2
//		    OrderItemsDTO orderItemsDTO32= new OrderItemsDTO();
//		    orderItemsDTO32.setOrderItemsId(null);
//		    orderItemsDTO32.setItem("TestB");
//		    orderItemsDTO32.setQuantity(Long.valueOf(3));
//		    orderItemsDTO32.setPrice(Double.valueOf(46.36));
//		    orderItemsDTO32.setOrderId(null);
//		    orderItemDTOList3.add(orderItemsDTO32);
		    
		    ordersDTO_3.setOrderItems(orderItemDTOList3);
		    
		    customersDTO.setOrders(ordersDTOlist);
		    
		    customersFormData.setDelItemIds("15");
		    customersFormData.setDelOrderIds("125");
		    customersFormData.setCustomersDTO(customersDTO);
		    
		    ResponseEntity<CustomersDTO> customerDTO = customerQueryController.updateCustomers(customersFormData);

//		    List<CustomersDTO> customerDTOList = customerQueryRepository.getCustomersList();	
//			System.out.println("####=="+customerDTO.getBody().getFirstName());
			
		    System.out.println("=============================================");
//		    System.out.println("New Updated Form: "+customerDTOList);
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
	        for(CustomersDTO i: customerDTOList)
	        	System.out.println(i);
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
