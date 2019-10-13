package com.axiom.searchapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class AppException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	public AppException(Exception e) {
		
	}
	
}
