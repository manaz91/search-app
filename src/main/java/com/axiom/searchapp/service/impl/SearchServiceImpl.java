package com.axiom.searchapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.axiom.searchapp.entity.Mobile;
import com.axiom.searchapp.exception.AppException;
import com.axiom.searchapp.model.AppServicesRequest;
import com.axiom.searchapp.model.ResponseModel;
import com.axiom.searchapp.model.SearchRequest;
import com.axiom.searchapp.service.RestService;
import com.axiom.searchapp.service.SearchService;
import com.axiom.searchapp.utility.AppConstants;
import com.axiom.searchapp.utility.RequestType;
import com.axiom.searchapp.utility.ServiceConstants;
import com.axiom.searchapp.utility.Utility;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private RestService restService;
	
	@Value("${search.service.url}")
	private String serviceUrl;
	
	@Override
	public Object search(SearchRequest searchRequest, HttpServletRequest request) throws AppException {
		ResponseModel responseModel = Utility.getResponseModel(AppConstants.SUCCESS);
		AppServicesRequest appServicesRequest = Utility.getAppServiceRequest(ServiceConstants.SEARCH_SERVICE, null);
		String response = restService.getRestResponse(appServicesRequest, RequestType.MSREST, serviceUrl, request);

		List<Mobile> mobiles = Utility.jsonToList(response, Mobile.class);

		List<Mobile> result = filterResult(searchRequest, mobiles);

		responseModel.setData(result);
		return responseModel;
	}

	@Override
	public List<Mobile> filterResult(SearchRequest searchRequest, List<Mobile> mobiles) {

		List<Predicate<Mobile>> allPredicates = new ArrayList<Predicate<Mobile>>();
		
		(!ObjectUtils.isEmpty(searchRequest.getAnnounceDate()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getAnnounceDate().equals(mobile.getRelease().getAnnounceDate()));

		(!ObjectUtils.isEmpty(searchRequest.getAudioJack()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getAudioJack().equals(mobile.getHardware().getAudioJack()));
		
		(!ObjectUtils.isEmpty(searchRequest.getBattery()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getBattery().equals(mobile.getHardware().getBattery()));
		
		(!ObjectUtils.isEmpty(searchRequest.getBrand()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getBrand().equals(mobile.getBrand()));
		
		(!ObjectUtils.isEmpty(searchRequest.getGps()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getGps().equals(mobile.getHardware().getGps()));
		
		(!ObjectUtils.isEmpty(searchRequest.getPhone()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getPhone().equals(mobile.getPhone()));
		
		(!ObjectUtils.isEmpty(searchRequest.getPriceEur()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getPriceEur().equals(mobile.getRelease().getPriceEur()));
		
		(!ObjectUtils.isEmpty(searchRequest.getResolution()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getResolution().equals(mobile.getResolution()));
		
		(!ObjectUtils.isEmpty(searchRequest.getSim()) ? allPredicates : new ArrayList<Predicate<Mobile>>())
			.add(mobile -> searchRequest.getSim().equals(mobile.getSim()));
		
		return mobiles.stream()
				 	  .filter(allPredicates.stream().reduce(predicate -> true, Predicate::and))
				 	  .collect(Collectors.toList());
	}

}
