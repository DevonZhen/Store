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
import com.store.dto.CustomersDTO;
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
        	customersList = jdbcTemplate.query(qq_SQL, new CustomersMapper());
	
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
    	  	  OrdersDTO ordersDTO = new OrdersDTO();
	    	  ordersDTO.setOrderId(rs.getLong("Order_Id"));  
	    	  ordersDTO.setOrderStatus(rs.getString("Order_Status"));
	    	  ordersDTO.setOrderDate(rs.getDate("Order_Date"));
	    	  ordersDTO.setStoreId(rs.getLong("Store_Id"));
    		
    		return customersDTO;
    	}
    }
 
}
