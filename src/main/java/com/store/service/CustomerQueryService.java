package com.store.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.store.dto.CustomersDTO;
import com.store.dto.CustomersFormData;
import com.store.repository.CustomerQueryRepository;


@Service
@Transactional
public class CustomerQueryService {

	@Autowired
	private CustomerQueryRepository customerQueryRepository;

	//1. Retrieve ALL 
	public List<CustomersDTO> getCustomersList()
	{
		List<CustomersDTO> customers = customerQueryRepository.getCustomersList();
		
		return customers;
	}

	//2. Update All 
	public CustomersDTO updateCustomers(CustomersFormData customersFormData) 
	{
		CustomersDTO customersDTO = customerQueryRepository.updateCustomers(customersFormData);

		return customersDTO;
	}		

}
