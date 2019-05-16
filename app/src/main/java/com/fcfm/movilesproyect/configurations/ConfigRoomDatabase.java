package com.fcfm.movilesproyect.configurations;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fcfm.movilesproyect.db.dao.ProjectDao;
import com.fcfm.movilesproyect.db.models.Project;

@Database( entities = { Project.class }, version = 1 )
public abstract class ConfigRoomDatabase extends RoomDatabase {
	
	public abstract ProjectDao projectDao( );
	
	private static volatile ConfigRoomDatabase INSTANCE;
	
	public static ConfigRoomDatabase getDatabase( final Context ctx ) {
		if ( ConfigRoomDatabase.INSTANCE == null ) {
			synchronized ( ConfigRoomDatabase.class ) {
				if ( ConfigRoomDatabase.INSTANCE == null )
					ConfigRoomDatabase.INSTANCE = Room.databaseBuilder(
							ctx.getApplicationContext( ), ConfigRoomDatabase.class, "DB" ).build( );
			}
		}
		
		return ConfigRoomDatabase.INSTANCE;
	}
}
