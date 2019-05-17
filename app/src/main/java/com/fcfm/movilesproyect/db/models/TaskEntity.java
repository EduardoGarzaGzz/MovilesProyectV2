package com.fcfm.movilesproyect.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.fcfm.movilesproyect.db.converters.DataConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity( tableName = "task")
public class TaskEntity {
	
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
	
	@SerializedName( "urgency" )
	@Expose
	@ColumnInfo( name = "urgency" )
	public int urgency;
	
	@SerializedName( "final_date" )
	@Expose
	@ColumnInfo( name = "final_date" )
	@TypeConverters( DataConverter.class )
	public Date final_date;
	
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
	
	public TaskEntity( ) {
	}
	
	public TaskEntity( long id, String title, String description, int urgency, Date final_date,
	                   Date create_at, Date update_at ) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.urgency = urgency;
		this.final_date = final_date;
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
	
	public int getUrgency( ) {
		return urgency;
	}
	
	public void setUrgency( int urgency ) {
		this.urgency = urgency;
	}
	
	public Date getFinal_date( ) {
		return final_date;
	}
	
	public void setFinal_date( Date final_date ) {
		this.final_date = final_date;
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
	
	@Override
	public String toString( ) {
		return "TaskEntity{" + "id=" + id + ", title='" + title + '\'' + ", description='" +
		       description + '\'' + ", urgency=" + urgency + ", final_date=" + final_date +
		       ", create_at=" + create_at + ", update_at=" + update_at + '}';
	}
}
