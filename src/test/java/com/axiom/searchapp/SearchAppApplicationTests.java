package com.axiom.searchapp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.axiom.searchapp.entity.Hardware;
import com.axiom.searchapp.entity.Mobile;
import com.axiom.searchapp.entity.Release;
import com.axiom.searchapp.model.SearchRequest;
import com.axiom.searchapp.service.SearchService;
import com.axiom.searchapp.utility.Utility;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SearchAppApplicationTests {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchAppApplicationTests.class);
    
    @Autowired
	private SearchService searchService;
    
	private List<Mobile> mobileData;
	
	private String jsonString;
    
	@BeforeAll
	void beforeAll() {
		mobileData = createTestData();
		jsonString = "[ { \"id\": 25846, \"brand\": \"Apple\", \"phone\": \"Apple iPad Pro 12.9 (2018)\", \"picture\": \"https://cdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-129-2018.jpg\", \"sim\": \"Nano-SIM eSIM\", \"resolution\": \"2048 x 2732 pixels\", \"release\": { \"announceDate\": \"2018 October\", \"priceEur\": \"1100\" }, \"hardware\": { \"audioJack\": \"No\", \"gps\": \"Yes with A-GPS\", \"battery\": \"Li-Po 9720 mAh battery (36.71 Wh)\" } }, { \"id\": 22895, \"brand\": \"Apple\", \"phone\": \"Apple iPad Pro 11\", \"picture\": \"https://cdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-11-2018.jpg\", \"sim\": \"Nano-SIM eSIM\", \"resolution\": \"1668 x 2388 pixels\", \"release\": { \"announceDate\": \"2018 October\", \"priceEur\": \"880\" }, \"hardware\": { \"audioJack\": \"No\", \"gps\": \"Yes with A-GPS\", \"battery\": \"Li-Po 7812 mAh battery (29.45 Wh)\" } }, { \"id\": 28136, \"brand\": \"Apple\", \"phone\": \"Apple iPhone XS Max\", \"picture\": \"https://cdn2.gsmarena.com/vv/bigpic/apple-iphone-xs-max-new1.jpg\", \"sim\": \"Single SIM\", \"resolution\": \"1242 x 2688 pixels\", \"release\": { \"announceDate\": \"2018 September\", \"priceEur\": \"1250\" }, \"hardware\": { \"audioJack\": \"No\", \"gps\": \"Yes with A-GPS\", \"battery\": \"Li-Ion 3174 mAh battery\" } } ]";
        LOGGER.info("Before all test method");
    }

    @AfterAll
    void afterAll() {
        LOGGER.info("After all test method");
    }
    
    /*
	 * Test Method to assert if jsontoList method works. Takes string json data as request.
	 *  data filtered based on search criteria
	 * */
	@Test
	void jsonToListTest() {
        LOGGER.info("JSON to List Test method");
		assertEquals(mobileData, Utility.jsonToList(jsonString, Mobile.class));
	}
	
	/*
	 * Test Method to assert if filter query works. Takes a array of objects created by createTestData() method,
	 *  data filtered based on search criteria
	 * */	
	@Test
	void filterTest() {
		
        LOGGER.info("Filter Query Test method");
        
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setPhone("Apple iPad Pro 12.9 (2018)");
        List<Mobile> expectedResult =  Arrays.asList(new Mobile(25846, "Apple", "Apple iPad Pro 12.9 (2018)", "https://cdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-129-2018.jpg", "Nano-SIM eSIM", "2048 x 2732 pixels", 
															new Release("2018 October", 1100.0), 
															new Hardware("No", "Yes with A-GPS", "Li-Po 9720 mAh battery (36.71 Wh)")));
        
		assertEquals(expectedResult, searchService.filterResult(searchRequest, mobileData));
	}
	
	/*
	 * Method to create test data. Generates array of objects of Mobile class.
	 * 
	 * */	
	private List<Mobile> createTestData() {
		return Arrays.asList(
				new Mobile(25846, "Apple", "Apple iPad Pro 12.9 (2018)", "https://cdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-129-2018.jpg", "Nano-SIM eSIM", "2048 x 2732 pixels", 
						new Release("2018 October", 1100.0), 
						new Hardware("No", "Yes with A-GPS", "Li-Po 9720 mAh battery (36.71 Wh)") ), 
				
				new Mobile(22895, "Apple", "Apple iPad Pro 11", "https://cdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-11-2018.jpg", "Nano-SIM eSIM", "1668 x 2388 pixels", 
						new Release("2018 October", 880.0), 
						new Hardware("No", "Yes with A-GPS", "Li-Po 7812 mAh battery (29.45 Wh)") ),
				
				new Mobile(28136, "Apple", "Apple iPhone XS Max", "https://cdn2.gsmarena.com/vv/bigpic/apple-iphone-xs-max-new1.jpg", "Single SIM", "1242 x 2688 pixels", 
						new Release("2018 September", 1250.0), 
						new Hardware("No", "Yes with A-GPS", "Li-Ion 3174 mAh battery") )
		);
	}

}

