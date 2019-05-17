package com.fcfm.movilesproyect.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.fcfm.movilesproyect.db.models.Project;

import java.util.List;

@Dao
public interface ProjectDao {
	
	@Query( "SELECT * FROM project ORDER BY title ASC" )
	LiveData< List< Project > > getAll( );
	
	@Query( "SELECT * FROM project WHERE id = :idProject" )
	LiveData< Project > getProjectById( int idProject );
	
	@Insert
	void insert( Project project );
	
	@Update
	void update( Project project );
	
	@Query( "DELETE FROM project" )
	void deleteAll( );
	
	@Query( "DELETE FROM project WHERE id = :id_project" )
	void deleteById( int id_project );
	
}
