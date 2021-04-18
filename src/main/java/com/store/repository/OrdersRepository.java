package com.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.store.domain.Orders;

@Repository
public interface OrdersRepository extends BaseRepository<Orders, Long>{
	public List<Orders> findAll();
}
