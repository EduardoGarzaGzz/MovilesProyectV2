package com.fcfm.movilesproyect.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.fcfm.movilesproyect.db.models.CitaEntity;
import com.fcfm.movilesproyect.db.models.TaskEntity;

import java.util.List;

@Dao
public interface CitaDao {
	
	@Query( "SELECT * FROM cita ORDER BY title ASC" )
	LiveData< List< CitaEntity > > getAll( );
	
	@Query( "SELECT * FROM cita WHERE id = :idCita" )
	LiveData< CitaEntity > getTaskById( int idCita );
	
	@Insert
	void insert( CitaEntity entity );
	
	@Query( "DELETE FROM cita" )
	void deleteAll( );
	
}
