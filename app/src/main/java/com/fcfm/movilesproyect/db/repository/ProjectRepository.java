package com.fcfm.movilesproyect.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.fcfm.movilesproyect.configurations.ConfigRoomDatabase;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.dao.ProjectDao;
import com.fcfm.movilesproyect.db.models.Project;

import java.util.List;

public class ProjectRepository {
	private ProjectDao                  dao;
	private LiveData< List< Project > > allProjects;
	
	public ProjectRepository( Application app ) {
		ConfigRoomDatabase db = ConfigRoomDatabase.getDatabase( app );
		this.dao = db.projectDao( );
		this.allProjects = this.dao.getAll( );
	}
	
	public LiveData< List< Project > > getAll( ) {
		return this.allProjects;
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
			
			for ( Project project : projects ) {
				Utilidades.printLog( "Insertando proyecto: " + project.toString( ) );
				this.dao.insert( project );
			}
			
			return null;
		}
	}
}
