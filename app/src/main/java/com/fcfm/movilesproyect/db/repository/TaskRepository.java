package com.fcfm.movilesproyect.db.repository;

import android.app.Application;

import com.fcfm.movilesproyect.configurations.ConfigRoomDatabase;
import com.fcfm.movilesproyect.db.dao.TaskDao;

public class TaskRepository {
	
	private TaskDao dao;
	
	public TaskRepository( Application app ) {
		this.dao = ConfigRoomDatabase.getDatabase( app ).taskDao( );
	}
}
