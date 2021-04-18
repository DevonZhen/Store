package com.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.domain.Customers;

@Repository
public interface CustomerRepository extends BaseRepository<Customers, Long> {
	
	public List<Customers> findAll();
	
	
//	@Query("SELECT c FROM Character c JOIN FETCH c.bags b WHERE c.id = ?1")

}
