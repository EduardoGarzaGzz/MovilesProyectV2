package com.fcfm.movilesproyect.db.apis;

import com.fcfm.movilesproyect.db.models.TaskEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskAPIService {
	
	@GET( "/api/v1/projects/{id}/tasks\"" )
	Call< TaskEntity[] > getProjects( @Path( "id" ) long id );
	
	@POST( "/api/v1/project/{id}/task" )
	Call< TaskEntity > registerProject( @Path( "id" ) long id, @Body TaskEntity task );
	
	@POST( "/api/v1/task/update" )
	Call< TaskEntity > updateProject( @Body TaskEntity task );
}
