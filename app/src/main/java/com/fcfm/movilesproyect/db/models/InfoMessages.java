package com.fcfm.movilesproyect.db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class InfoMessages {
	
	@SerializedName( "id" )
	@Expose
	private long id;
	
	@SerializedName( "user_provoke" )
	@Expose
	private User user_provoke;
	
	@SerializedName( "user_addressee" )
	@Expose
	private User user_addressee;
	
	@SerializedName( "messages" )
	@Expose
	private String messages;
	
	@SerializedName( "hash_action" )
	@Expose
	private String hash_action;
	
	@SerializedName( "create_at" )
	@Expose
	private Date create_at;
	
	public InfoMessages( ) {
	}
	
	public InfoMessages( long id, User user_provoke, User user_addressee, String messages,
	                     String hash_action, Date create_at ) {
		this.id = id;
		this.user_provoke = user_provoke;
		this.user_addressee = user_addressee;
		this.messages = messages;
		this.hash_action = hash_action;
		this.create_at = create_at;
	}
	
	public long getId( ) {
		return id;
	}
	
	public void setId( long id ) {
		this.id = id;
	}
	
	public User getUser_provoke( ) {
		return user_provoke;
	}
	
	public void setUser_provoke( User user_provoke ) {
		this.user_provoke = user_provoke;
	}
	
	public User getUser_addressee( ) {
		return user_addressee;
	}
	
	public void setUser_addressee( User user_addressee ) {
		this.user_addressee = user_addressee;
	}
	
	public String getMessages( ) {
		return messages;
	}
	
	public void setMessages( String messages ) {
		this.messages = messages;
	}
	
	public String getHash_action( ) {
		return hash_action;
	}
	
	public void setHash_action( String hash_action ) {
		this.hash_action = hash_action;
	}
	
	public Date getCreate_at( ) {
		return create_at;
	}
	
	public void setCreate_at( Date create_at ) {
		this.create_at = create_at;
	}
	
	@Override
	public String toString( ) {
		return "InfoMessages{" + "id=" + id + ", user_provoke=" + user_provoke.getNombres( ) +
		       ", user_addressee=" + user_addressee.getNombres( ) + ", messages='" + messages +
		       '\'' + ", hash_action='" + hash_action + '\'' + ", create_at=" + create_at + '}';
	}
}
