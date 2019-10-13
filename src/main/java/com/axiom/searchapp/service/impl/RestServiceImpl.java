package com.axiom.searchapp.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axiom.searchapp.exception.AppException;
import com.axiom.searchapp.model.AppServicesRequest;
import com.axiom.searchapp.service.RestService;
import com.axiom.searchapp.utility.RequestType;
import com.axiom.searchapp.utility.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestServiceImpl implements RestService {

	private static final Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	/**
	 * This method is used to perform the rest request and get the rest response for a rest API call.
	 * @param appServicesRequest
	 * @param requestType
	 * @param url
	 * @param request
	 * @return
	 * @throws AppException
	 */
	@Override
	public <T> T getRestResponse(AppServicesRequest appServicesRequest, RequestType requestType, String url, HttpServletRequest request) throws AppException {
		T response = null;
		try {
			HttpHeaders headers = createHeaders(appServicesRequest);
			HttpEntity<Object> entity = createEntity(appServicesRequest, headers);
			HttpMethod httpMethod = getHttpMethod(appServicesRequest);
			
			logger.info("Service request {}", Utility.logAPIRequest(appServicesRequest));
			
			Class<T> responseType = getClassResponseType();		
			logger.info("Response output type :: {}", responseType.getName());	
			
			ResponseEntity<T> serviceResponse = restTemplate.exchange(null == url ? "" : url, httpMethod, entity, responseType);
			logger.info("RESPONSE FROM REST SERVICE {}", serviceResponse);
			
			if(HttpStatus.OK.equals(serviceResponse.getStatusCode())){
				response = serviceResponse.getBody();
			}
		
		} catch (Exception e) {
			logger.error(Utility.getExceptionMessage(e), e);
			throw e;
		}
		return response;	
	}

	private HttpMethod getHttpMethod(AppServicesRequest appServicesRequest) {
		HttpMethod httpMethod = HttpMethod.GET;
		if (HttpMethod.POST.toString().equalsIgnoreCase(appServicesRequest.getRequestMethod())) {
			httpMethod = HttpMethod.POST;
		}
		return httpMethod;
	}

	/**
	 * This method is used to create Request body for the rest API call.
	 * 
	 * @param appServicesRequest
	 * @param requestType
	 * @param headers
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	private <V> HttpEntity<V> createEntity(AppServicesRequest appServicesRequest, HttpHeaders headers) throws AppException 
	{
		HttpEntity<V> httpEntity = null;
		String restRequest= null;
		try {
			restRequest = mapper.writeValueAsString(appServicesRequest.getInputRequest());
			httpEntity = (HttpEntity<V>) new HttpEntity<>(restRequest, headers);
		} catch (Exception e) {
			logger.info(Utility.getExceptionMessage(e), e);
			throw new AppException(e);
		}
		return httpEntity;
	}

	/**
	 * This method creates headers for the rest API call.
	 * 
	 * @param appServicesRequest
	 * @param requestType
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public HttpHeaders createHeaders(AppServicesRequest appServicesRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("type", appServicesRequest.getType());
		headers.set("identifierName", appServicesRequest.getIdentifierName());
		return headers;
	}

	/**
	 * This method is used to set the Response type after rest API call to catch the response.
	 * 
	 * @param requestType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getClassResponseType() {
		Class<T> clazz = (Class<T>) String.class;
		return (T) clazz;
	}
	
}
