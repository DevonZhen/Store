package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.dto.CustomersDTO;
import com.store.dto.OrdersDTO;
import com.store.resturl.RESTUrls;
import com.store.service.CustomerService;
import com.store.service.OrdersService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class CustomerController {

	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrdersService ordersService;
	
	//Successful
	@RequestMapping(value = RESTUrls.URL_Customers_All, method = RequestMethod.GET)
	public ResponseEntity<List<CustomersDTO>> findAll(){
		try {
			List<CustomersDTO> customersDTOList= customerService.findAll();
			System.out.println("Customer All: "+customersDTOList);
			if(customersDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<CustomersDTO>>(customersDTOList, HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error calling findAll()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	//Insert Customer
	@PostMapping(value=RESTUrls.URL_PostCustomer, produces="application/json")
	public CustomersDTO insertCustomer(@RequestBody CustomersDTO customersDTO) {
		System.out.println("Inserting Customer...");
		System.out.println("CustomerDTO => "+customersDTO);
//		CustomersDTO responseDTO=null;
		return null;
	}
	
	//Insert Order
	@PostMapping(value=RESTUrls.URL_PostOrder, produces="application/json")
	public OrdersDTO insertOrder(@RequestBody OrdersDTO ordersDTO) {
		System.out.println("Inserting Order...");
		System.out.println("OrderDTO => "+ordersDTO);
//		OrdersDTO  responseDTO=null;
		return null;
	}
	
	
	//Update Customer
	@PostMapping(value = RESTUrls.URL_PostCustomer, produces = "application/json")
	public CustomersDTO updateCustomer(@RequestBody CustomersDTO customersDTO) {
		System.out.println("Updating Customer...");
		System.out.println("CustomersDTO ==> "+customersDTO);
//		try {
//			customerService.customerUpdate();
//		}catch(Exception e) {
//			log.error("Error calling updateCustomer()", e);
//		}
		return null;
	}
	
	//Update Order
	@PostMapping(value = RESTUrls.URL_PostOrder, produces = "application/json")
	public OrdersDTO updateOrder(@RequestBody OrdersDTO ordersDTO) {
		System.out.println("Updating Order...");
		System.out.println("OrdersDTO ==> "+ordersDTO);
//		try {
//			customerService.orderUpdate();
//		}catch(Exception e) {
//			log.error("Error calling updateOrder()", e);
//		}
		return null;
	}
	
	
	
	
	//Delete Customer
	@DeleteMapping(value = RESTUrls.URL_DeleteCustomer, produces = "application/json")
	public void deleteCustomer(@PathVariable String id) {
		System.out.println("Deleting Customer... ");
		System.out.println("Customer Id => "+id);
//		try {
//			Long cid = Long.parseLong(id);
//			customerService.deleteCustomer(cid);
//		} catch (Exception e) {
//		     log.error("Error calling deleteCustomer()", e);
//		}
	}
	
	
	//Delete Order
	@DeleteMapping(value = RESTUrls.URL_DeleteOrder, produces = "application/json")
	public void deleteOrder(@PathVariable String id) {
		System.out.println("Deleting Order... ");
		System.out.println("Order Id => "+id);
//		try {
//			Long oid = Long.parseLong(id);
//			customerService.deleteOrder(oid);
//		} catch (Exception e) {
//			log.error("Error calling deleteOrder()", e);
//		}
	}
}
