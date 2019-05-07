package com.fcfm.movilesproyect.apis;

import com.fcfm.movilesproyect.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPIService {
	
	@GET( "/api/v1/users" )
	Call< User[] > getUsers( );
	
	@GET( "/api/v1/user/{id}" )
	Call< User > getUser( @Path( "id" ) String id );
	
	@POST( "/api/v1/user/login" )
	Call< User > login( @Body User user );
	
	@POST( "/api/v1/users" )
	Call< User > registrarUser( @Body User user );
	
}
