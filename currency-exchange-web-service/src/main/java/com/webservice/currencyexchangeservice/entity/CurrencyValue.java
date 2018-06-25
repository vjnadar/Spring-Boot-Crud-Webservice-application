package com.webservice.currencyexchangeservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="exchange_value")

public class CurrencyValue 

{
	
	@Id
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	
	private Long id;
	
	@Column(name="currency_from")
		
	private String from;
	
	@Column(name="currency_to")
	
	private String to;
	
	@Column(name="conversion_multiple")
	
	BigDecimal conversionMultiple;
	
	@Column(name="port")
	
	private int port;


	public CurrencyValue() 
	
	{
		

		
	}
	

	public CurrencyValue(Long id, String from, String to, BigDecimal conversionMultiple, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.port = port;
	}



	public Long getId() 
	
	{
		
		return id;
		
	}

	public void setId(Long id)
	
	{
		
		this.id = id;
		
	}

	public String getFrom() 
	
	{
		
		return from;
		
	}

	public void setFrom(String from) 
	
	{
		
		this.from = from;
		
	}

	public String getTo() 
	
	{
		
		return to;
		
	}

	public void setTo(String to) 
	
	{
		
		this.to = to;
		
	}

	public BigDecimal getConversionMultiple() 
	
	{
		
		return conversionMultiple;
		
	}

	public void setConversionMultiple(BigDecimal conversionMultiple)
	
	{
		
		this.conversionMultiple = conversionMultiple;
		
	}
	
	public int getPort() 
	
	{
		
		return port;
		
	}

	public void setPort(int port) 
	
	{
		
		this.port = port;
		
	}

	@Override
	public String toString() {
		return "CurrencyValue [id=" + id + ", from=" + from + ", to=" + to + ", conversionMultiple="
				+ conversionMultiple + ", port=" + port + "]";
	}
	
	
	
	
	
	
	

}
