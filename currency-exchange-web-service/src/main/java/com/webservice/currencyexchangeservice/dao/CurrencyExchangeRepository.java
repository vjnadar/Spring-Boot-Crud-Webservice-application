package com.webservice.currencyexchangeservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.webservice.currencyexchangeservice.entity.CurrencyValue;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyValue, Long> 

{

	CurrencyValue findByFromAndTo(String from,String to);
	
}
