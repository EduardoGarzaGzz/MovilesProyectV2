package com.fcfm.movilesproyect.db.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.fcfm.movilesproyect.db.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
	
	private TaskRepository repository;
	
	public TaskViewModel( @NonNull Application application ) {
		super( application );
		
		this.repository = new TaskRepository( application );
	}
}
