package com.axiom.searchapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axiom.searchapp.exception.AppException;
import com.axiom.searchapp.model.SearchRequest;
import com.axiom.searchapp.service.SearchService;
import com.axiom.searchapp.swagger.SwaggerResponse;
import com.axiom.searchapp.swagger.SwaggerTags;
import com.axiom.searchapp.utility.AppConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(AppConstants.MOBILE)
@Api(tags = AppConstants.SEARCH_API_TAG)
public class SearchResource {
	
	@Autowired
	private SearchService searchService;

	@ApiOperation(value = SwaggerTags.VALUE_SEARCH, notes = SwaggerTags.NOTES_SEARCH)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = SwaggerResponse.SEARCH ),
	@ApiResponse(code = 422, message = SwaggerResponse.SERVICE_EXCEPTION)})
    @GetMapping(AppConstants.SEARCH)
    public Object search(SearchRequest searchRequest, HttpServletRequest request) throws AppException {

    	return searchService.search(searchRequest, request);

    }
}
