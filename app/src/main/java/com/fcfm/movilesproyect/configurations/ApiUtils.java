package com.fcfm.movilesproyect.configurations;

import com.fcfm.movilesproyect.db.apis.InfoMsgAPIService;
import com.fcfm.movilesproyect.db.apis.ProjectAPIService;
import com.fcfm.movilesproyect.db.apis.TaskAPIService;
import com.fcfm.movilesproyect.db.apis.UserAPIService;

public class ApiUtils {
	
	private ApiUtils( ) {}
	
	//	private static final String BASE_URL = "https://moviles-api.herokuapp.com";
	private static final String BASE_URL = "http://192.168.15.16:8080";
	
	public static UserAPIService getUserAPIService( ) {
		return RetrofitClient.getClient( BASE_URL ).create( UserAPIService.class );
	}
	
	public static InfoMsgAPIService getInfoMsgApiService( ) {
		return RetrofitClient.getClient( BASE_URL ).create( InfoMsgAPIService.class );
	}
	
	public static ProjectAPIService getProjectService( ) {
		return RetrofitClient.getClient( BASE_URL ).create( ProjectAPIService.class );
	}
	
	public static TaskAPIService getTaskService( ) {
		return RetrofitClient.getClient( BASE_URL ).create( TaskAPIService.class );
	}
}