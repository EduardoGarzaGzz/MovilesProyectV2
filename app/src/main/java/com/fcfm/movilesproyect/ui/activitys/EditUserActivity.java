package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IEditUserMVP;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.presenter.EditUserPresenter;
import com.squareup.picasso.Picasso;


import java.io.File;

import java.io.IOException;

public class EditUserActivity extends AppCompatActivity implements IEditUserMVP.View {
	
	private IEditUserMVP.Presenter presenter;
	
	private TextView nombres;
	private TextView apellidos;
	private TextView username;
	private TextView correo;
	private TextView celular;
	private TextView change_password;
	private TextView password_verfi;
	
	private ImageView img_perfil;
	private ImageView img_fondo;
	
	private ImageButton btn_camara_perfil;
	private ImageButton btn_camara_fondo;
	private ImageButton btn_galery_perfil;
	private ImageButton btn_galery_fondo;
	
	private ImageButton btn_save_img_perfil;
	private ImageButton btn_save_img_fondo;
	
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_edit_user );
		
		this.nombres = ( TextView ) findViewById( R.id.tv_edit_user_nombres );
		this.apellidos = ( TextView ) findViewById( R.id.tv_edit_user_apellidos );
		this.username = ( TextView ) findViewById( R.id.tv_edit_user_username );
		this.correo = ( TextView ) findViewById( R.id.tv_edit_user_correo );
		this.celular = ( TextView ) findViewById( R.id.tv_edit_user_celular );
		this.change_password = ( TextView ) findViewById( R.id.tv_edit_user_change_password );
		this.password_verfi = ( TextView ) findViewById( R.id.tv_edit_user_password_verifi );
		
		this.img_perfil = ( ImageView ) findViewById( R.id.iv_edit_user_img_perfil );
		this.img_fondo = ( ImageView ) findViewById( R.id.iv_edit_user_img_backgroud );
		
		this.btn_camara_perfil = ( ImageButton ) findViewById( R.id.btn_edit_user_camera_perfil );
		this.btn_camara_fondo = ( ImageButton ) findViewById( R.id.btn_edit_user_camera_fondo );
		this.btn_galery_perfil = ( ImageButton ) findViewById( R.id.btn_edit_user_galery_perfil );
		this.btn_galery_fondo = ( ImageButton ) findViewById( R.id.btn_edit_user_galery_fondo );
		
		this.btn_save_img_perfil = ( ImageButton ) findViewById(
				R.id.btn_edit_user_save_img_perfil );
		this.btn_save_img_fondo = ( ImageButton ) findViewById( R.id.btn_edit_user_save_img_fondo );
		
		this.btn_actualizar = ( Button ) findViewById( R.id.btn_edit_user_actualizar );
		this.btn_cancelar = ( Button ) findViewById( R.id.btn_edit_user_cancelar );
		
		this.presenter = new EditUserPresenter( this );
		this.presenter.init( );
		
		Utilidades.checkPermisos( this );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		this.btn_camara_perfil.setOnClickListener( this.presenter.clickCamaraPerfil( ) );
		this.btn_camara_fondo.setOnClickListener( this.presenter.clickCamaraFondo( ) );
		this.btn_galery_perfil.setOnClickListener( this.presenter.clickGaleryPerfil( ) );
		this.btn_galery_fondo.setOnClickListener( this.presenter.clickGaleryFondo( ) );
		
		this.btn_save_img_perfil.setOnClickListener( this.presenter.clickSavePerfil( ) );
		this.btn_save_img_fondo.setOnClickListener( this.presenter.clickSaveFondo( ) );
		
		this.btn_actualizar.setOnClickListener( this.presenter.clickActualizar( ) );
		this.btn_cancelar.setOnClickListener( this.presenter.clickCancelar( ) );
		
		
	}
	
	@Override
	public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions,
	                                        @NonNull int[] grantResults ) {
		super.onRequestPermissionsResult( requestCode, permissions, grantResults );
		
		if ( requestCode == Utilidades.CODE_PERMISOS )
			Utilidades.printToast( this, "Perfecto continuemos" );
	}
	
	@Override
	protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data ) {
		super.onActivityResult( requestCode, resultCode, data );
		
		Bitmap bitmap = null;
		
		if ( Utilidades.CODE_CAMERA_PERFIL == requestCode ||
		     Utilidades.CODE_CAMERA_FONDO == requestCode )
			bitmap = ( Bitmap ) data.getExtras( ).get( "data" );
		
		if ( Utilidades.CODE_GALERY_PERFIL == requestCode ||
		     Utilidades.CODE_GALERY_FONDO == requestCode ) {
			
			try {
				Uri img_uri = data.getData( );
				bitmap = MediaStore.Images.Media.getBitmap( this.getContentResolver( ), img_uri );
			}catch ( IOException e ) {
				e.printStackTrace( );
			}catch ( NullPointerException e ) {
				e.printStackTrace( );
			}
		}
		
		switch ( requestCode ) {
			case Utilidades.CODE_GALERY_PERFIL:
			case Utilidades.CODE_CAMERA_PERFIL:
				this.img_perfil.setImageBitmap( bitmap );
				break;
			case Utilidades.CODE_GALERY_FONDO:
			case Utilidades.CODE_CAMERA_FONDO:
				this.img_fondo.setImageBitmap( bitmap );
				break;
			
		}
		
	}
	
	
	@Override
	public void setData( User user ) {
		
		Utilidades.printLog( "Actualizado data del usuario en el activity" );
		
		this.nombres.setText( user.getNombres( ) );
		this.apellidos.setText( user.getApellidos( ) );
		this.username.setText( user.getUsername( ) );
		this.correo.setText( user.getCorreo( ) );
		this.celular.setText( user.getCelular( ) );
		
		if ( user.getImg_perfil( ) != null && ! user.getImg_perfil( ).equals( "" ) )
			Picasso.get( ).load( user.getImg_perfil_uri( ) ).into( this.img_perfil );
		
		if ( user.getImg_fondo( ) != null && ! user.getImg_fondo( ).equals( "" ) )
			Picasso.get( ).load( user.getImg_fondo_uri( ) ).into( this.img_fondo );
		
		
	}
	
	@Override
	public User getData( ) {
		User user = new User( );
		
		user.setId( User.getUser_active( ).getId( ) );
		user.setUsername( this.username.getText( ).toString( ) );
		user.setNombres( this.nombres.getText( ).toString( ) );
		user.setApellidos( this.apellidos.getText( ).toString( ) );
		user.setCorreo( this.correo.getText( ).toString( ) );
		
		if ( this.change_password.getText( ).toString( ).trim( ).length( ) >= 3 &&
		     this.change_password.getText( ).toString( ).trim( )
		                         .equals( this.password_verfi.getText( ).toString( ).trim( ) ) )
			user.setPassword( this.change_password.getText( ).toString( ).trim( ) );
		else user.setPassword( User.getUser_active( ).getPassword( ) );
		
		user.setCelular( this.celular.getText( ).toString( ) );
		user.setImg_perfil( User.getUser_active( ).getImg_perfil( ) );
		user.setImg_fondo( User.getUser_active( ).getImg_fondo( ) );
		
		return user;
	}
	
	@Override
	public Context getContext( ) {
		return this;
	}
	
	@Override
	public AppCompatActivity getActivity( ) {
		return this;
	}
	
	@Override
	public File getImgPerfil( ) {
		return Utilidades.createFileDrawable( this, this.img_perfil.getDrawable( ) );
	}
	
	@Override
	public File getImgFondo( ) {
		return Utilidades.createFileDrawable( this, this.img_fondo.getDrawable( ) );
	}
	
}
