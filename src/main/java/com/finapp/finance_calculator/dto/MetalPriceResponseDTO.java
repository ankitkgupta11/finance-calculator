package com.finapp.finance_calculator.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MetalPriceResponseDTO {
	private Long id; 
	private String metal;
	private String purity;
	private BigDecimal price;
	private BigDecimal priceChangePercent;
	private String unit;
	private LocalDateTime updatedAt;

	public MetalPriceResponseDTO() {
	}

	public MetalPriceResponseDTO(Long id, String metal, String purity, BigDecimal price, BigDecimal priceChangePercent,
			String unit, LocalDateTime updatedAt) {
		this.id = id;
		this.metal = metal;
		this.purity = purity;
		this.price = price;
		this.priceChangePercent = priceChangePercent;
		this.unit = unit;
		this.updatedAt = updatedAt;
	}

	// Getters and Setters

	public String getMetal() {
		return metal;
	}

	public void setMetal(String metal) {
		this.metal = metal;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceChangePercent() {
		return priceChangePercent;
	}

	public void setPriceChangePercent(BigDecimal priceChangePercent) {
		this.priceChangePercent = priceChangePercent;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}