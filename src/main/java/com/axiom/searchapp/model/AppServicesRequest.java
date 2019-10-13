package com.axiom.searchapp.model;

import java.util.Map;

import lombok.Data;

@Data
public class AppServicesRequest {
	
	private String type;

	private String ServiceName;

	private String identifierName;
	
	private String requestMethod;
	
	private Object inputRequest;
	
	private Map<String, String> requestHeaders;
	
}
