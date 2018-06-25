package com.webservice.currencyexchangeservice.servicepackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservice.currencyexchangeservice.dao.CurrencyExchangeRepository;
import com.webservice.currencyexchangeservice.entity.CurrencyValue;

@Service
public class ExchangeServiceImpl implements ExchangeService

{
	@Autowired
	
	CurrencyExchangeRepository repo;
	
	@Override
	public CurrencyValue findById(Long id) 
	
	{
		
		CurrencyValue value=repo.findById(id).get();
		
		return value;
	}
	
	@Override
	public CurrencyValue findByFromAndTo(String from, String to) 
	
	{
		
		return repo.findByFromAndTo(from, to);
		
	}

	@Override
	public List<CurrencyValue> findAll() 
	
	{
		
		return repo.findAll();
		
	}

	@Override
	public void save(CurrencyValue value) 
	
	{
		
		repo.save(value);
		
	}

	@Override
	public void delete(CurrencyValue value) 
	
	{
		
		repo.delete(value);
		
	}



}
