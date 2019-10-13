package com.axiom.searchapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axiom.searchapp.entity.Mobile;
import com.axiom.searchapp.exception.AppException;
import com.axiom.searchapp.model.SearchRequest;

public interface SearchService {

	public Object search(SearchRequest searchRequest, HttpServletRequest request) throws AppException;

	public List<Mobile> filterResult(SearchRequest searchRequest, List<Mobile> mobiles);

}
