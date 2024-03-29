package com.fcfm.movilesproyect.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.fcfm.movilesproyect.db.converters.DataConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity( tableName = "project" )
public class Project {
	
	public static List< Project > projects = new ArrayList<>( );
	
	@SerializedName( "id" )
	@Expose
	@PrimaryKey( autoGenerate = true )
	@ColumnInfo( name = "id" )
	public long id;
	
	@SerializedName( "title" )
	@Expose
	@ColumnInfo( name = "title" )
	public String title;
	
	@SerializedName( "description" )
	@Expose
	@ColumnInfo( name = "description" )
	public String description;
	
	@SerializedName( "create_at" )
	@Expose
	@ColumnInfo( name = "create_at" )
	@TypeConverters( DataConverter.class )
	public Date create_at;
	
	@SerializedName( "update_at" )
	@Expose
	@ColumnInfo( name = "update_at" )
	@TypeConverters( DataConverter.class )
	public Date update_at;
	
	public Project( ) {
	}
	
	public Project( long id, String title, String description, Date create_at, Date update_at ) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	
	public long getId( ) {
		return id;
	}
	
	public void setId( long id ) {
		this.id = id;
	}
	
	public String getTitle( ) {
		return title;
	}
	
	public void setTitle( String title ) {
		this.title = title;
	}
	
	public String getDescription( ) {
		return description;
	}
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	public Date getCreate_at( ) {
		return create_at;
	}
	
	public void setCreate_at( Date create_at ) {
		this.create_at = create_at;
	}
	
	public Date getUpdate_at( ) {
		return update_at;
	}
	
	public void setUpdate_at( Date update_at ) {
		this.update_at = update_at;
	}
	
	public static Project getProjectById( long id ) {
		
		for ( Project project : Project.projects ) {
			if ( project.id == id ) return project;
		}
		
		return new Project( );
	}
	
	@Override
	public String toString( ) {
		return this.title;
	}
}
