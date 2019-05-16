package com.fcfm.movilesproyect.db.apis;

import com.fcfm.movilesproyect.db.models.InfoMessages;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InfoMsgAPIService {
	
	@GET( "/api/v1/user/{id}/info_msg" )
	Call< InfoMessages[] > getUserAllInfoMsg( @Path( "id" ) String id );
	
}
