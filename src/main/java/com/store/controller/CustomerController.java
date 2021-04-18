package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
//	@RequestMapping(value = RESTUrls.URL_Customers_All, method = RequestMethod.GET)
//	public ResponseEntity<List<CustomersDTO>> findAll(){
//		try {
//			List<CustomersDTO> customersDTOList= customerService.findAll();
//			System.out.println("Customer All: "+customersDTOList);
//			if(customersDTOList.isEmpty())
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			
//			return new ResponseEntity<List<CustomersDTO>>(customersDTOList, HttpStatus.OK);
//		}catch (Exception e) {
//			log.error("Error calling findAll()", e);
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
	
	@RequestMapping(value = RESTUrls.URL_Customers_All, method = RequestMethod.GET)
	public ResponseEntity<List<OrdersDTO>> findAll(){
		try {
			List<OrdersDTO> ordersDTOList= ordersService.findAll();
			System.out.println("Orders All: "+ordersDTOList);
			if(ordersDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<OrdersDTO>>(ordersDTOList, HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error calling findAll()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
