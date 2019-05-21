package com.fcfm.movilesproyect.db.repository;

import android.app.Application;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.ConfigRoomDatabase;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.apis.ProjectAPIService;
import com.fcfm.movilesproyect.db.apis.TaskAPIService;
import com.fcfm.movilesproyect.db.dao.TaskDao;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.TaskEntity;
import com.fcfm.movilesproyect.db.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;

public class TaskRepository {
	
	private TaskAPIService service;
	private ProjectAPIService projectService;
	private TaskDao dao;
	
	public TaskRepository( Application app ) {
		this.dao = ConfigRoomDatabase.getDatabase( app ).taskDao( );
		this.service = ApiUtils.getTaskService( );
		this.projectService = ApiUtils.getProjectService( );
	}
	
	public LiveData< List< TaskEntity > > getAllTaskByUserId( ) {
		
		this.service.getTaskByUserId( User.getUser_active( ).getId( ) ).enqueue( new Callback< TaskEntity[] >( ) {
			@Override
			public void onResponse( Call< TaskEntity[] > call, Response< TaskEntity[] > response ) {
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					Utilidades.printResponseBody( response );
					
					Utilidades.printLog( "Se estan actualizando las tareas" );
					
					new InsertAsyncTask( dao ).execute( response.body( ) );
					
					Utilidades.printLog( "Se an actualizando las tareas" );
					
				}
			}
			
			@Override
			public void onFailure( Call< TaskEntity[] > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
		
		return this.dao.getAll( );
	}
	
	public void getAllProjectByUserId( Context ctx, long id ) {
		this.projectService.getProjects( id ).enqueue( new Callback< Project[] >( ) {
			@Override
			public void onResponse( Call< Project[] > call, Response< Project[] > response ) {
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					Utilidades.printResponseBody( response );
					
					Utilidades.printLog( "Se a actualizado los proyectos" );
					Project.projects = Arrays.asList( response.body( ) );
				}
			}
			
			@Override
			public void onFailure( Call< Project[] > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
	}
	
	
	public void newTask( final Context ctx, TaskEntity taskEntity, long id ) {
		
		this.service.registerProject( id, taskEntity ).enqueue( new Callback< TaskEntity[] >( ) {
			@Override
			public void onResponse( Call< TaskEntity[] > call, Response< TaskEntity[] > response ) {
				
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					Utilidades.printResponseBody( response );
					Utilidades.printToastSuccess( ctx, "La tarea se a guardado" );
					
					new InsertAsyncTask( dao ).execute( response.body( ) );
				}
				
			}
			
			@Override
			public void onFailure( Call< TaskEntity[] > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
		
	}
	
	private static class InsertAsyncTask extends AsyncTask< TaskEntity, Void, Void > {
		
		private TaskDao taskDao;
		
		public InsertAsyncTask( TaskDao taskDao ) {
			this.taskDao = taskDao;
		}
		
		@Override
		protected Void doInBackground( TaskEntity... taskEntities ) {
			this.taskDao.deleteAll( );
			Utilidades.printLog( "Agregando nuevas Tasks DB interna" );
			
			for ( TaskEntity task : taskEntities ) {
				Utilidades.printLog( "Add task: " + task.toString( ) );
				this.taskDao.insert( task );
			}
			
			
			return null;
		}
	}
	
	public void updateTask( final Context context, TaskEntity taskEntity ) {
		
		this.service.updateProject( taskEntity.projectId, taskEntity ).enqueue( new Callback< TaskEntity[] >( ) {
			@Override
			public void onResponse( Call< TaskEntity[] > call, Response< TaskEntity[] > response ) {
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					Utilidades.printResponseBody( response );
					Utilidades.printToastSuccess( context, "La tarea se a actualizado" );
					
					new InsertAsyncTask( dao ).execute( response.body( ) );
				}
			}
			
			@Override
			public void onFailure( Call< TaskEntity[] > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
		
	}
	
	private static class UpdateAsyncTask extends AsyncTask< TaskEntity, Void, Void > {
		
		private TaskDao taskDao;
		
		public UpdateAsyncTask( TaskDao taskDao ) {
			this.taskDao = taskDao;
		}
		
		@Override
		protected Void doInBackground( TaskEntity... taskEntities ) {
			this.taskDao.deleteAll( );
			Utilidades.printLog( "Actualizando Tasks DB interna" );
			
			for ( TaskEntity task : taskEntities ) {
				Utilidades.printLog( "Update task: " + task.toString( ) );
				this.taskDao.update( task );
			}
			
			return null;
		}
	}
}
