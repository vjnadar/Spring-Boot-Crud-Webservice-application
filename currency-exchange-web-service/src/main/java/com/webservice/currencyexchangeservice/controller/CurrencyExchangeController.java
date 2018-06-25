package com.webservice.currencyexchangeservice.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservice.currencyexchangeservice.entity.CurrencyValue;
import com.webservice.currencyexchangeservice.servicepackage.ExchangeService;

@RestController
public class CurrencyExchangeController 

{
	
	@Autowired
	Environment environment;
	
	@Autowired
	
	ExchangeService repository;
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	
	public CurrencyValue getConversionMultiple(@PathVariable("from") String from, @PathVariable("to") String to)
	
	{
		
		CurrencyValue value=repository.findByFromAndTo(from, to);
					
		value.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		log.info("{}",value);
		
		return value;
		
	}
	
	
	@GetMapping("/getAll")
	
	public List<CurrencyValue> getAll()  
	
	{
		
		
		List<CurrencyValue> value= repository.findAll();
		

		
		return value;
		
	}
	
	@PostMapping("/postValues")
	
	public ResponseEntity<Object> postValues(@RequestBody CurrencyValue value)
	
	{
		
		value.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		System.out.println(value.toString());
		
		repository.save(value);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(value.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/deleteValue/{id}")
	
	public void deleteValue(@PathVariable("id") Long id)
	
	{
		
		CurrencyValue value=repository.findById(id);
		
		repository.delete(value);
				
		
		
	}
	
	

}
