package com.fcfm.movilesproyect.apis;

import com.fcfm.movilesproyect.models.InfoMessages;
import com.fcfm.movilesproyect.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InfoMsgAPIService {
	
	@GET( "/api/v1/user/{id}/info_msg" )
	Call< InfoMessages[] > getUserAllInfoMsg( @Path( "id" ) String id );
	
}
