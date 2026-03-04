package com.finapp.finance_calculator.vo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bike_variant")
@Getter
@Setter
public class BikeVariantVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "on_road_price", nullable = false)
	private double onRoadPrice;
	
	@Column(name = "ex_showroom_price", nullable = false)
	private double exShowroomPrice;
	
	@Column(name = "insurance_cost", nullable = false)
	private double insuranceCost;
	
	@Column(name = "rto_charges", nullable = false)
	private double rtoCharges;
	
	@Column(name = "other_charges", nullable = false)
	private double otherCharges;

	@Column(name = "image_url")
	private String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id", nullable = false)
	private BikeModelVO model;
	@Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
