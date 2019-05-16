package com.fcfm.movilesproyect.db.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.fcfm.movilesproyect.db.dao.ProjectDao;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.repository.ProjectRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
	
	private LiveData< List< Project > > projects;
	private ProjectRepository           repository;
	
	public ProjectViewModel( @NonNull Application application ) {
		super( application );
		
		this.repository = new ProjectRepository( application );
		this.projects = this.repository.getAll( );
	}
	
	public LiveData< List< Project > > getProjects( ) { return this.projects; }
	
	public void insertarProject( Project[] project ) {
		this.repository.insert( project );
	}
}
