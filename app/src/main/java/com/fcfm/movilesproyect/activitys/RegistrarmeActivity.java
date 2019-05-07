package com.fcfm.movilesproyect.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarmeActivity extends AppCompatActivity {
	
	private Context        this_context;
	private UserAPIService api;
	
	private TextView edittext_nombres;
	private TextView edittext_correo;
	private TextView edittext_password;
	private TextView edittext_password_verifi;
	
	private Button btn_registrar;
	private Button btn_cancelar;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_registrarme );
		
		this.this_context = this;
		
		this.api = ApiUtils.getUserAPIService( );
		
		this.btn_registrar = findViewById( R.id.btn_registro_registro );
		this.btn_cancelar = findViewById( R.id.btn_registro_cancelar );
		
		this.edittext_nombres = findViewById( R.id.edittext_registro_nombres );
		this.edittext_correo = findViewById( R.id.edittext_registro_correo );
		this.edittext_password = findViewById( R.id.edittext_registro_password );
		this.edittext_password_verifi = findViewById( R.id.edittext_registro_password_verifi );
	}
	
	@Override protected void onStart( ) {
		super.onStart( );
		
		this.btn_registrar.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
				
				if ( edittext_nombres.getText( ).length( ) < 2 ) {
					
					Toast.makeText( this_context, "El nombre es demaciado corto",
									Toast.LENGTH_LONG ).show( );
					return;
				}
				
				if ( ! edittext_password.getText( ).toString( )
										.equals( edittext_password_verifi.getText( )
																		 .toString( ) ) ) {
					
					Toast.makeText( this_context, "Las contraseñas deben conicidir",
									Toast.LENGTH_LONG ).show( );
					return;
				}
				
				Log.e( "MY LOG", "Iniciando peticion" );
				User user = new User( 0, "",
									  edittext_nombres.getText( ).toString( ),
									  "",
									  edittext_correo.getText( ).toString( ),
									  edittext_password.getText( ).toString( ),
									  "" );
				
				api.registrarUser( user ).enqueue( new Callback< User >( ) {
					@Override
					public void onResponse( Call< User > call, Response< User > response ) {
						
						if ( response.isSuccessful( ) ) {
							Log.e( "MY APP LOG", "Response peticion " + response.code( ) );
							Log.e( "MY APP LOG", response.body( ).toString( ) );
							
							Toast.makeText( this_context, "Bienvenido se a registrado correctamente", Toast.LENGTH_LONG ).show();
							startActivity( new Intent( this_context, LoginActivity.class ) );
							finish();
							
						}else {
							Log.e( "MY APP LOG", "Response peticion" + response.code( ) );
						}
						
					}
					
					@Override public void onFailure( Call< User > call, Throwable t ) {
						Log.e( "MY APP LOG", "Peticion FAIL" );
						Log.e( "MY APP LOG", t.getMessage( ) );
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
}
