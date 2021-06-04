package com.store.dto;
import lombok.Data;

@Data
public class StoresDTO {
	private Long id;
	private String storeName;
	private String phone;
	private String email;
	private String street;
	private Long zip;
}
