package com.axiom.searchapp.swagger;

public class SwaggerResponse {
	
	public static final String SERVICE_EXCEPTION = "{\"errorMessage\":\"System Error, Please Contact the Administrator.\",\"errorCode\":\"SERVICE_EXCEPTION\"}";
	
	public static final String SEARCH = "{ \"response\": \"Success\", \"data\": [ { \"id\": 28298, \"brand\": \"Apple\", \"phone\": \"Apple Watch Series 4\", \"picture\": \"https://cdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-steel.jpg\", \"sim\": \"eSIM\", \"resolution\": \"448 x 368 pixels\", \"release\": { \"announceDate\": \"2018 September\", \"priceEur\": \"700\" }, \"hardware\": { \"audioJack\": \"No\", \"gps\": \"Yes with A-GPS\", \"battery\": \"Li-Ion battery\" } }, { \"id\": 23131, \"brand\": \"Apple\", \"phone\": \"Apple Watch Series 4 Aluminum\", \"picture\": \"https://cdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-aluminum.jpg\", \"sim\": \"eSIM\", \"resolution\": \"448 x 368 pixels\", \"release\": { \"announceDate\": \"2018 September\", \"priceEur\": \"430\" }, \"hardware\": { \"audioJack\": \"No\", \"gps\": \"Yes with A-GPS\", \"battery\": \"Li-Ion battery\" } } ] }";
	
	private SwaggerResponse(){
		throw new IllegalAccessError("SwaggerResponse class");
	}
}