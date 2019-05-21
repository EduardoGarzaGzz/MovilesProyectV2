package com.fcfm.movilesproyect.db.apis;

import com.fcfm.movilesproyect.db.models.CitaEntity;
import com.fcfm.movilesproyect.db.models.User;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

import java.util.List;

public interface UserAPIService {
	
	@GET( "/api/v1/users" )
	Call< User[] > getUsers( );
	
	@GET( "/api/v1/user/{id}" )
	Call< User > getUser( @Path( "id" ) String id );
	
	@POST( "/api/v1/user/login" )
	Call< User > login( @Body User user );
	
	@POST( "/api/v1/users" )
	Call< User > registrarUser( @Body User user );
	
	@POST( "/api/v1/user/update" )
	Call< User > actualizarUser( @Body User user );
	
	@Multipart
	@POST( "/api/v1/{id}/save_img_perfil" )
	Call< User > saveImgPerfil( @Path( "id" ) long id, @Part MultipartBody.Part file );
	
	@Multipart
	@POST( "/api/v1/{id}/save_img_fondo" )
	Call< User > saveImgFondo( @Path( "id" ) long id, @Part MultipartBody.Part file );
	
	@GET( "/api/v1/user/{id}/getcitas" )
	Call< CitaEntity[] > getCitas( @Path( "id" ) long id );
	
	@POST( "/api/v1/user/{id}/addcita" )
	Call< CitaEntity > addCita( @Path( "id" ) long id, @Body CitaEntity cita );
	
	@POST( "/api/v1/cita/update" )
	Call< CitaEntity > updateCita( @Body CitaEntity cita );
}
