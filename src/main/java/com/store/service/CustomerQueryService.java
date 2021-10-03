package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.store.dto.CustomersDTO;
import com.store.repository.CustomerQueryRepository;


@Service
@Transactional
public class CustomerQueryService {

	@Autowired
	private CustomerQueryRepository customerQueryRepository;

	//1. Retrieve ALL Tables
	public List<CustomersDTO> getCustomersList(){
		List<CustomersDTO> customers = customerQueryRepository.getCustomersList();
		return customers;
	}

}
