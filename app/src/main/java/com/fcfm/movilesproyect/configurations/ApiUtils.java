package com.fcfm.movilesproyect.configurations;

import com.fcfm.movilesproyect.apis.InfoMsgAPIService;
import com.fcfm.movilesproyect.apis.UserAPIService;

public class ApiUtils {
	
	private ApiUtils( ) {}
	
	//	private static final String BASE_URL = "https://moviles-api.herokuapp.com";
	private static final String BASE_URL = "http://192.168.1.147:8080";
	
	public static UserAPIService getUserAPIService( ) {
		return RetrofitClient.getClient( BASE_URL ).create( UserAPIService.class );
	}
	
	public static InfoMsgAPIService getInfoMsgApiService( ) {
		return RetrofitClient.getClient( BASE_URL ).create( InfoMsgAPIService.class );
	}
}