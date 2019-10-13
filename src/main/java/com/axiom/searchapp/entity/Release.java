package com.axiom.searchapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Release {

	private String announceDate;
	
	private Double priceEur;
	
	public Release(String announceDate, Double priceEur) {
		this.announceDate = announceDate;
		this.priceEur = priceEur;
	}
	
}
