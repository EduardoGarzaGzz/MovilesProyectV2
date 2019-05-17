package com.fcfm.movilesproyect.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.ConfigRoomDatabase;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.apis.ProjectAPIService;
import com.fcfm.movilesproyect.db.dao.ProjectDao;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.ui.fragments.ListaProjectsFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
	private ProjectDao                  dao;
	private LiveData< List< Project > > allProjects;
	
	public ProjectRepository( Application app ) {
		ConfigRoomDatabase db = ConfigRoomDatabase.getDatabase( app );
		this.dao = db.projectDao( );
		this.allProjects = this.dao.getAll( );
	}
	
	public LiveData< List< Project > > getAll( ) {
		
		ProjectAPIService service = ApiUtils.getProjectService( );
		service.getProjects( User.getUser_active( ).getId( ) )
		       .enqueue( new Callback< Project[] >( ) {
			       @Override
			       public void onResponse( Call< Project[] > call,
			                               Response< Project[] > response ) {
				
				       Utilidades.printResponseCode( response );
				       if ( response.isSuccessful( ) ) {
					
					       Utilidades.printResponseBody( response );
					       Utilidades.printLog( "Response size: " + response.body( ).length );
					       insert( response.body( ) );
					
				       }
			       }
			
			       @Override
			       public void onFailure( Call< Project[] > call, Throwable t ) {
				       Utilidades.failPeticionApi( t );
			       }
		       } );
		
		return this.allProjects;
	}
	
	public LiveData< Project > getProjectById( int id ) {
		return this.dao.getProjectById( id );
	}
	
	public void newProject( final Context ctx, final Project project ) {
		ProjectAPIService service = ApiUtils.getProjectService( );
		service.registerProject( User.getUser_active( ).getId( ), project )
		       .enqueue( new Callback< Project >( ) {
			       @Override
			       public void onResponse( Call< Project > call, Response< Project > response ) {
				       Utilidades.printResponseCode( response );
				       if ( response.isSuccessful( ) ) {
					
					       Utilidades.printResponseBody( response );
					       Utilidades.printToast( ctx, "El proyecto se guardo" );
					       getAll( );
				       }
			       }
			
			       @Override
			       public void onFailure( Call< Project > call, Throwable t ) {
				       Utilidades.failPeticionApi( t );
			       }
		       } );
	}
	
	public void updateProject( final Context ctx, final Project project ) {
		ProjectAPIService service = ApiUtils.getProjectService( );
		service.updateProject( project ).enqueue( new Callback< Project >( ) {
			@Override
			public void onResponse( Call< Project > call, Response< Project > response ) {
				Utilidades.printResponseCode( response );
				if ( response.isSuccessful( ) ) {
					
					Utilidades.printResponseBody( response );
					Utilidades.printToast( ctx, "El proyecto se a actualizado" );
					getAll( );
				}
			}
			
			@Override
			public void onFailure( Call< Project > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
	}
	
	public void insert( Project[] project ) {
		new InsertAsyncTask( this.dao ).execute( project );
	}
	
	private static class InsertAsyncTask extends AsyncTask< Project, Void, Void > {
		private ProjectDao dao;
		
		public InsertAsyncTask( ProjectDao dao ) {
			this.dao = dao;
		}
		
		@Override
		protected Void doInBackground( Project... projects ) {
			
			this.dao.deleteAll( );
			Utilidades.printLog( "Eliminando todos los proyectos" );
			Utilidades.printLog( "Nuevos projectos: " + projects.length );
			
			for ( Project project : projects ) {
				Utilidades.printLog( "Insertando proyecto: " + project.toString( ) );
				this.dao.insert( project );
			}
			
			return null;
		}
	}
}
