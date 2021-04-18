package com.store.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//Loads the NJIT.properties file
@Configuration
@ComponentScan(basePackages = {"com.store"})
//classpath = Windows; file = Linux
@PropertySource( ignoreResourceNotFound=true, value= {
	"classpath:Store.properties",	
	"file:///${APPCONF}/SpringService/Store.properties"   
})
@EnableCaching
public class ApplicationConfiguration {

	//Set the Properties file into the placeHolderConfigurer()
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
