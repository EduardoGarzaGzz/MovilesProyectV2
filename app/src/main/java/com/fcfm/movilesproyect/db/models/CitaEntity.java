package com.fcfm.movilesproyect.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.fcfm.movilesproyect.db.converters.DataConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity( tableName = "cita" )
public class CitaEntity {
	
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
	
	@SerializedName( "log" )
	@Expose
	@ColumnInfo( name = "log" )
	public float log;
	
	@SerializedName( "lat" )
	@Expose
	@ColumnInfo( name = "lat" )
	public float lat;
	
	@SerializedName( "date" )
	@Expose
	@ColumnInfo( name = "date" )
	@TypeConverters( DataConverter.class )
	public Date date;
	
	public CitaEntity( ) {
	}
	
	public CitaEntity( long id, String title, String description, float log, float lat, Date date ) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.log = log;
		this.lat = lat;
		this.date = date;
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
	
	public float getLog( ) {
		return log;
	}
	
	public void setLog( float log ) {
		this.log = log;
	}
	
	public float getLat( ) {
		return lat;
	}
	
	public void setLat( float lat ) {
		this.lat = lat;
	}
	
	public Date getDate( ) {
		return date;
	}
	
	public void setDate( Date date ) {
		this.date = date;
	}
	
	@Override
	public String toString( ) {
		return "CitaEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", log=" + log +
				", lat=" + lat +
				", date=" + date +
				'}';
	}
}
