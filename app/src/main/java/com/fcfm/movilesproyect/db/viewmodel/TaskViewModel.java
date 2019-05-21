package com.fcfm.movilesproyect.db.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.models.TaskEntity;

import com.fcfm.movilesproyect.db.repository.TaskRepository;

import java.util.List;


public class TaskViewModel extends AndroidViewModel {
	
	private TaskRepository repository;
	
	public TaskViewModel( @NonNull Application application ) {
		super( application );
		
		
		this.repository = new TaskRepository( application );
	}
	
	public LiveData< List< TaskEntity > > getAllTaskByUserId( ) {
		return this.repository.getAllTaskByUserId( );
	}
	
	public void newTask( Context ctx, TaskEntity taskEntity, long id ) {
		
		if ( taskEntity.title == null || taskEntity.title.equals( "" ) || taskEntity.title.length( ) < 2 ) {
			Utilidades.printToastError( ctx, "El titulo no puede estar vacio" );
		}
		
		this.repository.newTask( ctx, taskEntity, id );
	}
	
	public void updateTask( Context ctx, TaskEntity taskEntity ) {
		this.repository.updateTask( ctx, taskEntity );
	}
	
	public void getAllProjectByUserId( Context ctx, long id ) {
		this.repository.getAllProjectByUserId( ctx, id );
	}
}
