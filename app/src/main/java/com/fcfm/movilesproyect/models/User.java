package com.fcfm.movilesproyect.models;

import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.interfaces.ILoginMVP;
import com.fcfm.movilesproyect.interfaces.IMainMVP;
import com.fcfm.movilesproyect.interfaces.IRegistrarmeMVP;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User implements IMainMVP.Model, ILoginMVP.Model {
	
	private static User user_active;
	
	@SerializedName( "id" )
	@Expose
	private long id;
	
	@SerializedName( "username" )
	@Expose
	private String username;
	
	@SerializedName( "nombres" )
	@Expose
	private String nombres;
	
	@SerializedName( "apellidos" )
	@Expose
	private String apellidos;
	
	@SerializedName( "correo" )
	@Expose
	private String correo;
	
	@SerializedName( "password" )
	@Expose
	private String password;
	
	@SerializedName( "celular" )
	@Expose
	private String celular;
	
	@SerializedName( "img_perfil" )
	@Expose
	private String img_perfil;
	
	@SerializedName( "img_fondo" )
	@Expose
	private String img_fondo;
	
	@SerializedName( "create_at" )
	@Expose
	private Date create_at;
	
	@SerializedName( "update_at" )
	@Expose
	private Date update_at;
	
	public User( ) {
	}
	
	public User( long id, String username, String nombres, String apellidos, String correo,
	             String password, String celular ) {
		this.id = id;
		this.username = username;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.password = password;
		this.celular = celular;
	}
	
	public long getId( ) {
		return id;
	}
	
	public void setId( long id ) {
		this.id = id;
	}
	
	public String getUsername( ) {
		return username;
	}
	
	public void setUsername( String username ) {
		this.username = username;
	}
	
	public String getNombres( ) {
		return nombres;
	}
	
	public void setNombres( String nombres ) {
		this.nombres = nombres;
	}
	
	public String getApellidos( ) {
		return apellidos;
	}
	
	public void setApellidos( String apellidos ) {
		this.apellidos = apellidos;
	}
	
	public String getCorreo( ) {
		return correo;
	}
	
	public void setCorreo( String correo ) {
		this.correo = correo;
	}
	
	public String getPassword( ) {
		return password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public String getCelular( ) {
		return celular;
	}
	
	public void setCelular( String celular ) {
		this.celular = celular;
	}
	
	public String getImg_perfil( ) {
		return img_perfil;
	}
	
	public void setImg_perfil( String img_perfil ) {
		this.img_perfil = img_perfil;
	}
	
	public String getImg_fondo( ) {
		return img_fondo;
	}
	
	public void setImg_fondo( String img_fondo ) {
		this.img_fondo = img_fondo;
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
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", nombres='" + nombres +
		       '\'' + ", apellidos='" + apellidos + '\'' + ", correo='" + correo + '\'' +
		       ", password='" + password + '\'' + ", celular='" + celular + '\'' +
		       ", img_perfil='" + img_perfil + '\'' + ", img_fondo='" + img_fondo + '\'' +
		       ", create_at=" + create_at + ", update_at=" + update_at + '}';
	}
	
	public static User getUser_active( ) {
		return user_active;
	}
	
	public static void setUser_active( User user_active ) {
		User.user_active = user_active;
	}
	
	@Override
	public void setUserActivo( User user ) {
		User.user_active = user;
		Utilidades.printLog( "Usuario activo: " + User.user_active.toString( ) );
	}
}
