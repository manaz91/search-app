package com.axiom.searchapp.service;

import javax.servlet.http.HttpServletRequest;

import com.axiom.searchapp.exception.AppException;
import com.axiom.searchapp.model.AppServicesRequest;
import com.axiom.searchapp.utility.RequestType;

public interface RestService {

	<T> T getRestResponse(AppServicesRequest appServicesRequest, RequestType requestType, String url, HttpServletRequest request) throws AppException;
	
}
