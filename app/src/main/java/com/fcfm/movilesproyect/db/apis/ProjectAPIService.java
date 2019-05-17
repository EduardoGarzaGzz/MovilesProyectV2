package com.fcfm.movilesproyect.db.apis;

import com.fcfm.movilesproyect.db.models.Project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjectAPIService {
	
	@GET( "/api/v1/user/{id}/projects" )
	Call< Project[] > getProjects( @Path( "id" ) long id );
	
	@POST( "/api/v1/user/{id}/projects" )
	Call< Project > registerProject( @Path( "id" ) long id, @Body Project project );
	
	@POST( "/api/v1/project/update" )
	Call< Project > updateProject( @Body Project project );
}
