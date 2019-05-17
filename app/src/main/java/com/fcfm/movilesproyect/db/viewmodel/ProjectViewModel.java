package com.fcfm.movilesproyect.db.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.repository.ProjectRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
	
	private ProjectRepository           repository;
	
	public ProjectViewModel( @NonNull Application application ) {
		super( application );
		
		this.repository = new ProjectRepository( application );
	}
	
	public LiveData< Project > getProjectById( int id ) {
		return this.repository.getProjectById( id );
	}
	
	public LiveData< List< Project > > getProjects( ) { return this.repository.getAll( ); }
	
	public void newProject( Context ctx, Project project ) {
		this.repository.newProject( ctx, project );
	}
	
	public void updateProject( Context ctx, Project project ) {
		this.repository.updateProject( ctx, project );
	}
	
	public void insertarProject( Project[] project ) {
		this.repository.insert( project );
	}
}
