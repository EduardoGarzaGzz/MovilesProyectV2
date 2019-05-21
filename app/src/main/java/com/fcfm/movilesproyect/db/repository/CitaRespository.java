package com.fcfm.movilesproyect.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.ConfigRoomDatabase;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.apis.UserAPIService;
import com.fcfm.movilesproyect.db.dao.CitaDao;
import com.fcfm.movilesproyect.db.models.CitaEntity;
import com.fcfm.movilesproyect.db.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CitaRespository {
	
	private CitaDao citaDao;
	private UserAPIService userAPIService;
	
	public CitaRespository( Application app ) {
		this.citaDao = ConfigRoomDatabase.getDatabase( app ).citaDao( );
		this.userAPIService = ApiUtils.getUserAPIService( );
	}
	
	public LiveData< List< CitaEntity > > getAll( ) {
		
		this.userAPIService.getCitas( User.getUser_active( ).getId( ) ).enqueue( new Callback< CitaEntity[] >( ) {
			@Override
			public void onResponse( Call< CitaEntity[] > call, Response< CitaEntity[] > response ) {
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					Utilidades.printResponseBody( response );
					
					new InsertAsyncCita( citaDao ).execute( response.body( ) );
				}
			}
			
			@Override
			public void onFailure( Call< CitaEntity[] > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
		
		return this.citaDao.getAll( );
	}
	
	public void addCita( final Context ctx, CitaEntity citaEntity ) {
		
		this.userAPIService.addCita( User.getUser_active( ).getId( ), citaEntity ).enqueue(
				new Callback< CitaEntity >( ) {
					@Override
					public void onResponse( Call< CitaEntity > call, Response< CitaEntity > response ) {
						Utilidades.printResponseCode( response );
						
						if ( response.isSuccessful( ) ) {
							Utilidades.printResponseBody( response );
							Utilidades.printToastSuccess( ctx, "La cita se a guardado" );
							
							getAll( );
						}
					}
					
					@Override
					public void onFailure( Call< CitaEntity > call, Throwable t ) {
						Utilidades.failPeticionApi( t );
					}
				} );
		
	}
	
	private static class InsertAsyncCita extends AsyncTask< CitaEntity, Void, Void > {
		
		private CitaDao citaDao;
		
		public InsertAsyncCita( CitaDao citaDao ) {
			this.citaDao = citaDao;
		}
		
		@Override
		protected Void doInBackground( CitaEntity... citaEntities ) {
			
			this.citaDao.deleteAll( );
			Utilidades.printLog( "Agregando nuevas Citas DB interna" );
			
			for ( CitaEntity citaEntity : citaEntities ) {
				Utilidades.printLog( "Add cita: " + citaEntity.toString( ) );
				this.citaDao.insert( citaEntity );
			}
			
			return null;
		}
	}
}
