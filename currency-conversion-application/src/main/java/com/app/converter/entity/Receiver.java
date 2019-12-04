package com.app.converter.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Receiver {
	private Long id;

	@Size(max = 3, message = "Must be a 3 letter abbreviation")
	private String from;

	private String to;

	private BigDecimal conversionMultiple;

	private BigDecimal quantity;

	private BigDecimal changedCurrency;

	private Integer port;

	List<Receiver> list;

	public Receiver() {

	}

	public Receiver(Long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal changedCurrency, Integer port, List<Receiver> list) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.changedCurrency = changedCurrency;
		this.port = port;
		this.list = list;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj.getClass() == this.getClass()))
			return false;
		Receiver receiver = (Receiver) obj;
		return (receiver.getId() == this.getId() && receiver.getChangedCurrency() == this.changedCurrency);
	}

	@Override
	public int hashCode() {
		return this.getPort();
	}

	public List<Receiver> getList() {
		return list;
	}

	public void setList(List<Receiver> list) {
		this.list = list;
	}

	public BigDecimal getChangedCurrency() {
		return changedCurrency;
	}

	public void setChangedCurrency(BigDecimal changedCurrency) {
		this.changedCurrency = changedCurrency;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;

	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;

	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Receiver [id=" + id + ", from=" + from + ", to=" + to + ", conversionMultiple=" + conversionMultiple
				+ ", quantity=" + quantity + ", changedCurrency=" + changedCurrency + ", port=" + port + "]";
	}

}
