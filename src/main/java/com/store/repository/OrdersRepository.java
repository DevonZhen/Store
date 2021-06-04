package com.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.domain.Orders;

@Repository
public interface OrdersRepository extends BaseRepository<Orders, Long>{
	public List<Orders> findAll();
	
//	public Optional<Orders> findById(Long id);
	
	@Query("SELECT o FROM Orders o WHERE o.orderId = ?1")
	public Optional<Orders> findByOrderId(Long id);
	
	//Delete Query, remember to change SELECT
	@Query("SELECT o FROM FROM Orders o WHERE o.customerId = ?1")
	public Optional<Orders> deleteByCustomerId(Long id);
}
