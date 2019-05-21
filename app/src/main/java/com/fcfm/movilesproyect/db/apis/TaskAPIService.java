package com.fcfm.movilesproyect.db.apis;

import com.fcfm.movilesproyect.db.models.TaskEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskAPIService {
	
	@GET( "/api/v1/user/{id}/tasks" )
	Call< TaskEntity[] > getTaskByUserId( @Path( "id" ) long id );
	
	@POST( "/api/v1/project/{id}/task" )
	Call< TaskEntity[] > registerProject( @Path( "id" ) long id, @Body TaskEntity task );
	
	@POST( "/api/v1/task/update/{id}" )
	Call< TaskEntity[] > updateProject( @Path( "id" ) long id, @Body TaskEntity task );
	
}
