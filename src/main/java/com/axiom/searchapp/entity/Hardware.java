package com.axiom.searchapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Hardware {

	private String audioJack;
	
	private String gps;
	
	private String battery;

	public Hardware(String audioJack, String gps, String battery) {
		this.audioJack = audioJack;
		this.gps = gps;
		this.battery = battery;
	}
	
}
