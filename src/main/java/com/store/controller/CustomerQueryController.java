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
import com.store.dto.CustomersFormData;
import com.store.service.CustomerQueryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class CustomerQueryController {
	
	@Autowired
	CustomerQueryService customerQueryService;

	@RequestMapping(value = "/getCustomersList", method = RequestMethod.GET)
	public ResponseEntity<List<CustomersDTO>> getCustomersList(){
		try {
			List<CustomersDTO> customers= customerQueryService.getCustomersList();
			
			return new ResponseEntity<List<CustomersDTO>>(customers, HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/updateCustomers", produces = "application/json")
	public ResponseEntity<CustomersDTO> updateCustomers(@RequestBody CustomersFormData customersFormData) 
	{
		try {

			 CustomersDTO customersDTO  = customerQueryService.updateCustomers(customersFormData);

			 return new ResponseEntity<>(customersDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}


}
