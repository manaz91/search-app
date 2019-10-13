package com.axiom.searchapp.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.axiom.searchapp.model.AppServicesRequest;
import com.axiom.searchapp.model.ResponseModel;
import com.google.gson.Gson;

public class Utility {

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	private Utility() {
		throw new IllegalStateException("Utility class");
	}

	public static ResponseModel getResponseModel(String responseStatus) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setResponse(responseStatus);
		return responseModel;
	}

	public static String getExceptionMessage(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static String logAPIRequest(AppServicesRequest appServiceRequest) {
		return StringUtils.isEmpty(appServiceRequest.toString()) ? " " : appServiceRequest.toString();
	}

	public static AppServicesRequest getAppServiceRequest(String serviceName, Object requestObj) {
		AppServicesRequest appServicesRequest = new AppServicesRequest();
		appServicesRequest.setServiceName(serviceName);
		appServicesRequest.setInputRequest(requestObj);
		return appServicesRequest;
	}

	public static boolean isNull(Object value) {
		return value == null;
	}

	public static <T> List<T> jsonToList(String response, Class<T> clazz) {
		List<T> listObj = new ArrayList<>();
		Gson gson = new Gson();
		logger.info("Input string to be converted to JSON :: {}", response);
		if (!ObjectUtils.isEmpty(response)) {
			JSONArray jsonResponse = new JSONArray(response);
			for (int i = 0; i < jsonResponse.length(); i++) {
				listObj.add(gson.fromJson(jsonResponse.getJSONObject(i).toString(), clazz));
			}
		}
		return listObj;
	}
	
	public static String generateHashKey(String message, HttpServletRequest request) {
		message = message + request.getSession().getId();
		String hashedKey = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(AppConstants.SHA_1);
			byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
			hashedKey = new String(Hex.encode(hash));
		} catch (Exception exception) {
			logger.error("Error occurred while generating hash from String : {} :: {}", message, exception);
		}
		return hashedKey;
	}
}
