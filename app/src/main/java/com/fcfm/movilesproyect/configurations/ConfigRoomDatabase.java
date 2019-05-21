package com.fcfm.movilesproyect.configurations;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fcfm.movilesproyect.db.dao.CitaDao;
import com.fcfm.movilesproyect.db.dao.ProjectDao;
import com.fcfm.movilesproyect.db.dao.TaskDao;
import com.fcfm.movilesproyect.db.models.CitaEntity;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.TaskEntity;

@Database( entities = { Project.class, TaskEntity.class, CitaEntity.class }, version = 5 )
public abstract class ConfigRoomDatabase extends RoomDatabase {
	
	public abstract ProjectDao projectDao( );
	
	public abstract TaskDao taskDao( );
	
	public abstract CitaDao citaDao( );
	
	private static volatile ConfigRoomDatabase INSTANCE;
	
	public static ConfigRoomDatabase getDatabase( final Context ctx ) {
		if ( ConfigRoomDatabase.INSTANCE == null ) {
			synchronized ( ConfigRoomDatabase.class ) {
				if ( ConfigRoomDatabase.INSTANCE == null ) {
					ConfigRoomDatabase.INSTANCE = Room.databaseBuilder(
							ctx.getApplicationContext( ), ConfigRoomDatabase.class, "DB" )
							.fallbackToDestructiveMigration( )
							.build( );
				}
			}
		}
		
		return ConfigRoomDatabase.INSTANCE;
	}
}
