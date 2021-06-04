package com.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.domain.OrderItems;

@Repository
public interface OrderItemsRepository extends BaseRepository<OrderItems, Long>{
	
	public List<OrderItems> findAll();
	
	
	//Delete Query, remember to change SELECT
	@Query("SELECT i from Order_Items i WHERE i.ordersItemsId = ?1")
	public Optional<OrderItems> deleteByOrderId(Long ordersItemsId);

}
