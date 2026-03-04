package com.finapp.finance_calculator.vo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "metals")
public class MetalVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String purity;

    @Column(nullable = false)
    private String unit;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "metal", cascade = CascadeType.ALL)
    private List<MetalPriceVO> prices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<MetalPriceVO> getPrices() {
		return prices;
	}

	public void setPrices(List<MetalPriceVO> prices) {
		this.prices = prices;
	}
    
    
}