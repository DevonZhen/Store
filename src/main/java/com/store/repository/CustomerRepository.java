package com.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.domain.Customers;

@Repository
public interface CustomerRepository extends BaseRepository<Customers, Long> {
	
	public List<Customers> findAll();
	
		
	@Query("SELECT c FROM Customers c WHERE c.customerId = ?1")
	public Customers findCustomerId(Long customerId);

}
