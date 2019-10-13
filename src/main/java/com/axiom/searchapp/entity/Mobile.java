package com.axiom.searchapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Mobile {

	private Integer id;
	
	private String brand;
	
	private String phone;
	
	private String picture;
	
	private String sim;
	
	private String resolution;
	
	private Release release;
	
	private Hardware hardware;
	
	public Mobile(Integer id, String brand, String phone, String picture, String sim, String resolution, Release release, Hardware hardware) {
		this.id = id;
		this.brand = brand;
		this.phone = phone;
		this.picture = picture;
		this.sim = sim;
		this.resolution = resolution;
		this.release = release;
		this.hardware = hardware;
	}
	
}
