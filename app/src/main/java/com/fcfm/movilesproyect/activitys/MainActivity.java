package com.fcfm.movilesproyect.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.fragments.InformativeMessagesFragment;
import com.fcfm.movilesproyect.models.User;


import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	
	private UserAPIService api;
	
	private Button  btn_login;
	private Button  btn_registrarme;
	private Context this_context;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		this.btn_login = findViewById( R.id.btn_main_login );
		this.btn_registrarme = findViewById( R.id.btn_main_registrarme );
		this.this_context = this;
		
		this.api = ApiUtils.getUserAPIService( );
		
		this.checkLogin( );
	}
	
	@Override protected void onStart( ) {
		super.onStart( );
		
		this.btn_login.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
				startActivity( new Intent( this_context, LoginActivity.class ) );
			}
		} );
		
		this.btn_registrarme.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
				startActivity( new Intent( this_context, RegistrarmeActivity.class ) );
			}
		} );
		
		
	}
	
	private void checkLogin( ) {
		SharedPreferences prefs = getSharedPreferences( "info_user", Context.MODE_PRIVATE );
		
		if ( ! prefs.getString( "login_status", "NULL" ).equals( "true" ) )
			return;
		
		if ( prefs.getString( "login_fecha", "NULL" ).equals( "NULL" ) )
			return;
		else {
			
			Date login, hoy = null;
			int  diferencia = 0;
			
			try {
				login = new Date( prefs.getString( "login_fecha", "NULL" ) );
				hoy = new Date( );
				
				diferencia = ( int ) ( ( hoy.getTime( ) - login.getTime( ) ) / 1000 );
				Log.e( "MY APP LOG", String.valueOf( diferencia ) );
				
				int dias    = 0;
				int horas   = 0;
				int minutos = 0;
				if ( diferencia > 86400 ) {
					dias = ( int ) Math.floor( diferencia / 86400 );
					diferencia = diferencia - ( dias * 86400 );
				}
				if ( diferencia > 3600 ) {
					horas = ( int ) Math.floor( diferencia / 3600 );
					diferencia = diferencia - ( horas * 3600 );
				}
				if ( diferencia > 60 ) {
					minutos = ( int ) Math.floor( diferencia / 60 );
					diferencia = diferencia - ( minutos * 60 );
				}
				
				Log.e( "MY APP LOG",
					   "Hay " + dias + " dias, " + horas + " horas, " + minutos + " minutos y " +
					   diferencia + " segundos de diferencia" );
				
				if ( minutos > 30 ) {
					return;
				}
				
			}catch ( Exception e ) {
				e.printStackTrace( );
			}
			
		}
		
		Log.e( "MY APP LOG", "id_user : " + prefs.getString( "id_user", "0" ) );
		
		this.api.getUser( prefs.getString( "id_user", "0" ) ).enqueue(
				new Callback< User >( ) {
					@Override
					public void onResponse( Call< User > call, Response< User > response ) {
						Log.e( "MY APP LOG", "Response peticion " + response.code( ) );
						if ( response.isSuccessful( ) ) {
							Log.e( "MY APP LOG", response.body( ).toString( ) );
							
							User tmp_user = response.body( );
							
							if ( tmp_user.getId( ) != 0 && ! tmp_user.getCorreo( ).equals(
									"null" ) ) {
								
								
								User.setUser_active( tmp_user );
								
								startActivity( new Intent( this_context, DashbordActivity.class ) );
								finish( );
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
}
