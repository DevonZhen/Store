package com.store.junit;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.store.configuration.JNDIConfiguration;
import com.store.domain.Customers;
import com.store.repository.CustomerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, JNDIConfiguration.class })

     
public class SpringBootApplicationJunitTest {
	
	@Autowired
	CustomerRepository customerRepository;               
	
	
	
	@Test
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
	

}
