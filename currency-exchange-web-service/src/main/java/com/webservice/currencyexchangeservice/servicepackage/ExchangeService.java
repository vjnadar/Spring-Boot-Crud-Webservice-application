package com.webservice.currencyexchangeservice.servicepackage;

import java.util.List;

import com.webservice.currencyexchangeservice.entity.CurrencyValue;

public interface ExchangeService 

{
	public CurrencyValue findById(Long id);
	
	public CurrencyValue findByFromAndTo(String from,String to);
	
	public List<CurrencyValue> findAll();
	
	public void save(CurrencyValue value);
	
	public void delete(CurrencyValue value);
	
	

}
