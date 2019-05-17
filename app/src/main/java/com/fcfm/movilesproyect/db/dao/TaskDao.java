package com.fcfm.movilesproyect.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.fcfm.movilesproyect.db.models.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {
	
	@Query( "SELECT * FROM task ORDER BY title ASC" )
	LiveData< List< TaskEntity > > getAll( );
	
	@Query( "SELECT * FROM task WHERE id = :idTask" )
	LiveData< TaskEntity > getTaskById( int idTask );
	
	@Insert
	void insert( TaskEntity entity );
	
	@Update
	void update( TaskEntity entity );
	
	@Query( "DELETE FROM task" )
	void deleteAll( );
	
	@Query( "DELETE FROM task WHERE id = :idTask" )
	void deleteById( int idTask );
	
}
