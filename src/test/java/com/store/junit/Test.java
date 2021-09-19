package com.store.junit;

import java.util.ArrayList;
import java.util.List;

import com.store.domain.Orders;


public class Test {
	
	public static void main(String[] args) {
		
		List<Orders> listOri = new ArrayList<>();
		Orders order1 = new Orders();
		order1.setOrderId(1L);
		listOri.add(order1);
		Orders order2 = new Orders();
		order2.setOrderId(2L);
		listOri.add(order2);
		Orders order3 = new Orders();
		order3.setOrderId(3L);
		listOri.add(order3);
		
		List<Orders> listNew = new ArrayList<>();
		Orders order11 = new Orders();
		order11.setOrderId(1L);
		listNew.add(order11);
		
		Orders order22 = new Orders();
		order22.setOrderId(2L);
		listNew.add(order22);
		
		//Orders order33 = new Orders();
		//order33.setOrderId(3L);
		//listNew.add(order33);
		
		Orders order44 = new Orders();
		order44.setOrderId(null);
		order44.setOrderStatus("waiting");
		order44.setStoreId(2l);
		listNew.add(order44);


		System.out.println("order=="+listOri);
		List<Long> listDomain = new ArrayList<>();
		for (Orders orders:listOri) {
		     //System.out.println("order id=="+orders.getOrderId());
			 listDomain.add(orders.getOrderId());
		}	
		List<Long> listDTO = new ArrayList<>();
		for (Orders orders:listNew) {
		     //System.out.println("order id=="+orders.getOrderId());
			listDTO.add(orders.getOrderId());
		}	
		
		//Find the same orders
		List<Long> listSameOrders = new ArrayList<Long>(listDomain);
		listSameOrders.retainAll(listDTO);
        //System.out.println("list of the same orders=="+listSameOrders);
        
        //Find the miss orders
		List<Long> listRemovedOrders = new ArrayList<Long>(listDomain);
		listRemovedOrders.removeAll(listDTO);
        //System.out.println("list of removed orders=="+listRemovedOrders);
        
        //Find the new orders
        List<Orders> listNewOrders = new ArrayList<>();
		for (Orders orders:listNew) {
		     //System.out.println("order id=="+orders.getOrderId());
			if (orders.getOrderId()== null) {
				//System.out.println("order =="+orders);
			    listNewOrders.add(orders);
			}
		}	
        
		
		
		//----------------
		List<Orders> list_Ori = new ArrayList<>();
		Orders order_11 = new Orders();
		order_11.setOrderId(1L);
		list_Ori.add(order_11);
		Orders order_12 = new Orders();
		order_12.setOrderId(2L);
		list_Ori.add(order_12);

		List<Orders> list_New = new ArrayList<>();
		Orders order_21 = new Orders();
		order_21.setOrderId(1L);
		list_New.add(order_21);
		//Orders order_22 = new Orders();
		//order_22.setOrderId(2L);
		//list_New.add(order_22);
		
		List<Orders> sameOrders = new ArrayList<Orders>(list_Ori);
		sameOrders.retainAll(list_New);
        //System.out.println("same orders=="+sameOrders);

	}

}
