package com.store.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.domain.Orders;
import com.store.dto.CustomersDTO;
import com.store.dto.CustomersFormData;
import com.store.dto.OrderItemsDTO;
import com.store.dto.OrdersDTO;


@Repository
public class CustomerQueryRepository {
	
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired	
    public void setDataSource(DataSource dataSource) {  
	   this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CustomersDTO> getCustomersList() {
    	
    	List<CustomersDTO> customersList = new ArrayList<>();
 
       	String qq_SQL = " SELECT c.\"Customer_Id\", c.\"First_Name\", c.\"Last_Name\", c.\"Phone\", c.\"Email\", c.\"Street\", c.\"Zip\", "+
       	                "        o.\"Order_Id\", o.\"Order_Status\", o.\"Order_Date\", o.\"Store_Id\", "+
       			        "        i.\"Order_Items_Id\", i.\"Item\", i.\"Quantity\", i.\"Price\" "+
       		            "   FROM public.\"Customers\" c, public.\"Orders\" o "+
       			        "        LEFT OUTER JOIN public.\"Order_Items\" i "+
       		            "          ON o.\"Order_Id\" = i.\"Order_Id\" "+
		                "  WHERE c.\"Customer_Id\" = +o.\"Customer_Id\" "+
		                "  ORDER BY c.\"Customer_Id\", o.\"Order_Id\" ";  
       	
       	//Object[] sqlParameters = new Object[] {  }; 
       	
        try {
        	List<CustomersDTO> custList = new ArrayList<>();
        	custList = jdbcTemplate.query(qq_SQL, new CustomersMapper());
	
        	customersList = parseToCustomerDTO(custList);


        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        return customersList;
    }

    public final class CustomersMapper implements RowMapper<CustomersDTO> {
    	
    	public CustomersDTO mapRow(ResultSet rs, int rowNum) throws SQLException 
    	{
    		CustomersDTO customersDTO = new CustomersDTO();
   			customersDTO = new CustomersDTO();
    		customersDTO.setCustomerId(rs.getLong("Customer_Id"));
    		customersDTO.setFirstName(rs.getString("First_Name"));
    		customersDTO.setLastName(rs.getString("Last_Name"));
    		customersDTO.setPhone(rs.getString("PHone"));
    		customersDTO.setEmail(rs.getString("Email"));
    		customersDTO.setStreet(rs.getString("Street"));
    		customersDTO.setZip(rs.getLong("Zip"));
    		  //Add Order List
    		  List<OrdersDTO> ordersList = new ArrayList<>();
    		  OrdersDTO ordersDTO = new OrdersDTO();
	    	  ordersDTO.setOrderId(rs.getLong("Order_Id"));  
	    	  ordersDTO.setOrderStatus(rs.getString("Order_Status"));
	    	  ordersDTO.setOrderDate(rs.getDate("Order_Date"));
	    	  ordersDTO.setStoreId(rs.getLong("Store_Id"));
	    	     //Add Order Items List
    		     List<OrderItemsDTO> orderItemsList = new ArrayList<>();
	    	 	 OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
	    		 orderItemsDTO.setOrderItemsId(rs.getLong("Order_Items_Id"));
	    		 orderItemsDTO.setItem(rs.getString("Item"));
	    		 orderItemsDTO.setQuantity(rs.getLong("Quantity"));
	    		 orderItemsDTO.setPrice(rs.getDouble("Price"));
	    		 orderItemsList.add(orderItemsDTO);
	    		 
	    	ordersDTO.setOrderItems(orderItemsList);
    		ordersList.add(ordersDTO);
   		    customersDTO.setOrders(ordersList);
    		
    		return customersDTO;
    	}
    }
    
    private List<CustomersDTO> parseToCustomerDTO(List<CustomersDTO> customerslist) 
    {
    	List<CustomersDTO> customersList = new ArrayList<>();
    	
    	Set<Long> customerIdMap = new LinkedHashSet<>();
    	
    	for (CustomersDTO customerDTO : customerslist) {
    		 if (customerIdMap.add(customerDTO.getCustomerId())) {                                     
    			 CustomersDTO customersDTO = new CustomersDTO();
	    		 customersDTO.setCustomerId(customerDTO.getCustomerId());
	    		 customersDTO.setFirstName(customerDTO.getFirstName());
	    		 customersDTO.setLastName(customerDTO.getLastName());
	    		 customersDTO.setPhone(customerDTO.getPhone());
	    		 customersDTO.setEmail(customerDTO.getEmail());
	    		 customersDTO.setStreet(customerDTO.getStreet());
	    		 customersDTO.setZip(customerDTO.getZip());
	    		 customersDTO.setOrders(getOrdersByCustomerId(customerDTO.getCustomerId(), customerslist));
	    		 customersList.add(customersDTO);
    		 }
    	}
    	
    	return customersList;
    }
    
    private List<OrdersDTO> getOrdersByCustomerId(Long customerId, List<CustomersDTO> customerslist) 
    {
    	List<OrdersDTO> ordersList = new ArrayList<>();
    	
    	Set<Long> orderIdMap = new LinkedHashSet<>();
    	
    	for (CustomersDTO customerDTO : customerslist) {
    	   if (customerDTO.getCustomerId().equals(customerId)) {
	       	  for (OrdersDTO orderDTO : customerDTO.getOrders()) {
	       		if (orderIdMap.add(orderDTO.getOrderId())) {
				    OrdersDTO ordersDTO = new OrdersDTO();
					ordersDTO.setOrderId(orderDTO.getOrderId());
					ordersDTO.setOrderStatus(orderDTO.getOrderStatus());
					ordersDTO.setOrderDate(orderDTO.getOrderDate());
					ordersDTO.setStoreId(orderDTO.getStoreId());
					ordersDTO.setOrderItems(getOrderItemsByOrderId(orderDTO.getOrderId(), customerslist));
					ordersList.add(ordersDTO);
	       		} 
	    	  }
	   	   }
    	}
    	
    	return ordersList;
    }
    
    private List<OrderItemsDTO> getOrderItemsByOrderId(Long orderId, List<CustomersDTO> customerslist) 
    {
    	List<OrderItemsDTO> orderItemsList = new ArrayList<>();
    	
    	for (CustomersDTO customerDTO : customerslist) {
    	   for (OrdersDTO orderDTO : customerDTO.getOrders()) {
	    	  if (orderDTO.getOrderId().equals(orderId)) {
	    	    for (OrderItemsDTO orderItemDTO : orderDTO.getOrderItems()) {
       	    	     OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
			    	 orderItemsDTO.setOrderItemsId(orderItemDTO.getOrderItemsId());
			    	 orderItemsDTO.setItem(orderItemDTO.getItem());
			    	 orderItemsDTO.setQuantity(orderItemDTO.getQuantity());
			    	 orderItemsDTO.setPrice(orderItemDTO.getPrice());
			    	 orderItemsList.add(orderItemsDTO);
	    	    }
	    	  }
    	   }  
    	}
    	
    	return orderItemsList;
    }
    
    public CustomersDTO updateCustomers(CustomersFormData customersFormData) {
    	
    	CustomersDTO customersDTO = null;
    	try {
            System.out.println("Updating section customersDTO == "+customersFormData);
            customersDTO = customersFormData.getCustomersDTO();
            
			//1. Find the remain orders for UPDATE
            for(OrdersDTO o : customersDTO.getOrders())  {
            	try {
            		if(o.getOrderId()!=null) {
	            		String SQL_Update_Orders="UPDATE public.\"Orders\" "+
	            				"SET \"Order_Id\" = "+o.getOrderId()+", \"Order_Status\" = '"+o.getOrderStatus()+
	            				"', \"Order_Date\" = '"+o.getOrderDate()+"', \"Store_Id\" = "+o.getStoreId()+" "+
	            				"WHERE \"Order_Id\" = "+o.getOrderId();
	            		for(OrderItemsDTO i: o.getOrderItems()) {
	            			String SQL_Update_OrderItems="UPDATE public.\"Order_Items\""+
    	                    		"SET \"Order_Items_Id\" = "+i.getOrderItemsId()+", \"Item\" = "+i.getItem()+
    	                    		", \"Quantity\" = "+i.getQuantity()+", \"Price\" = "+i.getPrice()+", \"Order_Id\" = "+i.getOrderId();
	            			jdbcTemplate.update(SQL_Update_OrderItems, new CustomersMapper());
	            			System.out.println("Updating Order Items Completed");
	            		}
	            		
	            		System.out.println("Update This One..."+o.getOrderId());
//	            		jdbcTemplate.update(SQL_Update_Orders, new CustomersMapper());
            		}
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            }

            
	        //2. Find the removed orders for DELETE
            String SQL_Delete_Orders="DELETE FROM public.\"Orders\" WHERE \"Order_Id\" = "+customersFormData.getDelOrderIds();
            String SQL_Delete_OrderItems="DELETE FROM public.\"Orders_Items\" WHERE \"Order_Items_Id\" = "+customersFormData.getDelItemIds();
            try {
        		System.out.println("Deleting Orders..."+customersFormData.getDelOrderIds());
//            	jdbcTemplate.update(SQL_Delete_Orders, new CustomersMapper());
            	System.out.println("Deleting Order Items..."+customersFormData.getDelItemIds());
//            	jdbcTemplate.update(SQL_Delete_OrderItems, new CustomersMapper());
            	System.out.println("Deletion Completed...");

        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
            	
            
            
            
	        //3. Find the new orders for INSERT   
            for(OrdersDTO o : customersDTO.getOrders())  {
            	if(o.getOrderId()==null) {
            		
            		long orderSeqNum = jdbcTemplate.queryForObject("SELECT nextval('public.\"Seq_Orders\"');", Integer.class);
            		long itemSeqNum  = jdbcTemplate.queryForObject("SELECT nextval('public.\"Seq_Order_Items\"');", Integer.class);
            		
                    String SQL_Insert_Orders="INSERT INTO public.\"Orders\""+
                    		"(\"Order_Id\", \"Order_Status\", \"Order_Date\", \"Customer_Id\", \"Store_Id\") "+
                    		"VALUES("+orderSeqNum+", '"+o.getOrderStatus()+"', '"+o.getOrderDate()+"', 4, "+o.getStoreId()+") ";
                    try {
                		System.out.println("++++++++++++++ Inserting Orders... "+o);
//                		jdbcTemplate.update(SQL_Insert_Orders);
                		for(OrderItemsDTO i: o.getOrderItems()) {
    	                    String SQL_Insert_OrderItems="INSERT INTO public.\"Order_Items\""+
    	                    		"(\"Order_Items_Id\", \"Item\", \"Quantity\", \"Price\", \"Order_Id\") "+
    	                    		"VALUES("+itemSeqNum+", '"+i.getItem()+"', '"+i.getQuantity()+"', '"+i.getPrice()+"', '"+orderSeqNum+") ";
    	                    System.out.println(">>>>>>>> Inserting Order Items... "+i);
//    	            		jdbcTemplate.update(SQL_Insert_OrderItems);
//    	                    i.setOrderItemsId(itemSeqNum);
//    	                    i.setOrderId(itemSeqNum);
                        }
                		System.out.println("Inserting Completed... ");
//                    	o.setOrderId(orderSeqNum);
                    	
                	}catch(Exception e) {e.printStackTrace();}
            	}
            	else {
            		for(OrderItemsDTO i : o.getOrderItems()) {
                		if(o.getOrderId()!=null && i.getOrderItemsId()==null) {
                			System.out.println("<<<<<<<<<< Insert Order Item "+i);
                			long orderSeqNum = jdbcTemplate.queryForObject("SELECT nextval('public.\"Seq_Orders\"');", Integer.class);
                    		long itemSeqNum  = jdbcTemplate.queryForObject("SELECT nextval('public.\"Seq_Order_Items\"');", Integer.class);
                			String SQL_Insert_OrderItems="INSERT INTO public.\"Order_Items\""+
    	                    		"(\"Order_Items_Id\", \"Item\", \"Quantity\", \"Price\", \"Order_Id\") "+
    	                    		"VALUES("+itemSeqNum+", '"+i.getItem()+"', '"+i.getQuantity()+"', '"+i.getPrice()+"', '"+orderSeqNum+") ";
//    	            		jdbcTemplate.update(SQL_Insert_OrderItems);
                		}
                		else
                			System.out.println("Skip Insert Order Item #"+i.getOrderItemsId());
                	}
            	}
            	
            	
            }  
 
           	return customersDTO;
           		 
    		
    	} catch (Exception e) {
    		 e.printStackTrace();
    	}
		return customersDTO;
    }
    
 
}
