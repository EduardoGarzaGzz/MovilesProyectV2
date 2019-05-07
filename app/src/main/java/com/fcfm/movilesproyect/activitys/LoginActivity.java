package com.fcfm.movilesproyect.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.models.User;


import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
	
	private Context        this_context;
	private UserAPIService api;
	
	private TextView edittext_cuenta;
	private TextView edittext_password;
	
	private CheckBox check_recuerdame;
	
	private Button btn_login;
	private Button btn_cancelar;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_login );
		
		this.this_context = this;
		
		this.api = ApiUtils.getUserAPIService( );
		
		this.btn_login = findViewById( R.id.btn_login_login );
		this.btn_cancelar = findViewById( R.id.btn_login_cancelar );
		
		this.edittext_cuenta = findViewById( R.id.edittext_login_cuenta );
		this.edittext_password = findViewById( R.id.edittext_login_password );
		
		this.check_recuerdame = findViewById( R.id.check_login_recuerdame );
	}
	
	@Override protected void onStart( ) {
		super.onStart( );
		
		this.checkRecuerdame( );
		
		this.btn_login.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
				Log.e( "MY APP LOG", "Iniciando peticion" );
				
				String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
				
				User user = new User( 0, edittext_cuenta.getText( ).toString( ), "", "",
									  "",
									  edittext_password.getText( ).toString( ),
									  edittext_cuenta.getText( ).toString( ) );
				
				if ( edittext_cuenta.getText( ).toString( ).trim( ).matches( emailPattern ) ) {
					user.setCorreo( edittext_cuenta.getText( ).toString( ) );
				}
				api.login( user ).enqueue( new Callback< User >( ) {
					@Override
					public void onResponse( Call< User > call, Response< User > response ) {
						Log.e( "MY APP LOG", "Response peticion " + response.code( ) );
						if ( response.isSuccessful( ) ) {
							Log.e( "MY APP LOG", response.body( ).toString( ) );
							
							User tmp_user = response.body( );
							
							if ( tmp_user.getId( ) != 0 && ! tmp_user.getCorreo( ).equals(
									"null" ) ) {
								Toast.makeText( this_context, "Login correcto", Toast.LENGTH_LONG )
									 .show( );
								
								setRecuerdame( );
								setLoginExitoso( tmp_user.getId( ) );
								
								User.setUser_active( tmp_user );
								
								startActivity( new Intent( this_context, DashbordActivity.class ) );
								finish( );
							}else {
								Toast.makeText( this_context,
												"La cuenta o la contraseña es incorrecta",
												Toast.LENGTH_LONG )
									 .show( );
							}
							
						}else {
							Log.e( "MY APP LOG", "Response peticion " + response.code( ) );
						}
					}
					
					@Override public void onFailure( Call< User > call, Throwable t ) {
						Log.e( "MY APP LOG", "Peticion FAIL" );
						Log.e( "MY APP LOG", t.getMessage( ) + " : " + t.toString( ) );
					}
				} );
			}
		} );
		
		this.btn_cancelar.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
				startActivity( new Intent( this_context, MainActivity.class ) );
				finish( );
			}
		} );
	}
	
	public void checkRecuerdame( ) {
		
		SharedPreferences prefs = getSharedPreferences( "recuerdame", Context.MODE_PRIVATE );
		
		if ( Objects.equals( prefs.getString( "cuenta", "" ), "" ) )
			edittext_cuenta.setText( "" );
		else {
			edittext_cuenta.setText( prefs.getString( "cuenta", "" ) );
			check_recuerdame.setChecked( true );
		}
		
		
	}
	
	public void setRecuerdame( ) {
		if ( ! check_recuerdame.isChecked( ) )
			return;
		
		SharedPreferences prefs = getSharedPreferences( "recuerdame",
														Context.MODE_PRIVATE );
		SharedPreferences.Editor edit_prefs = prefs.edit( );
		
		edit_prefs.putString( "cuenta", edittext_cuenta.getText( ).toString( ) );
		edit_prefs.apply( );
	}
	
	public void setLoginExitoso( Long id ) {
		SharedPreferences prefs = getSharedPreferences( "info_user",
														Context.MODE_PRIVATE );
		SharedPreferences.Editor edit_prefs = prefs.edit( );
		
		edit_prefs.putString( "login_status", "true" );
		edit_prefs.putString( "login_fecha", new Date( ).toString( ) );
		edit_prefs.putString( "id_user", String.valueOf( id ) );
		edit_prefs.apply( );
		
		Log.w( "MY APP LOG", "login_fecha: " + prefs.getString( "login_fecha", "NULL" ) );
		Log.w( "MY APP LOG", "id_user: " + prefs.getString( "id_user", "NULL" ) );
	}
}
