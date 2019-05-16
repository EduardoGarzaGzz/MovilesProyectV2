package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IRegistrarmeMVP;
import com.fcfm.movilesproyect.presenter.RegistrarmePresenter;

public class RegistrarmeActivity extends AppCompatActivity implements IRegistrarmeMVP.View {
	
	private Context        this_context;
	private UserAPIService api;
	
	private IRegistrarmeMVP.Presenter presenter;
	
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

//		this.this_context = this;
//
//		this.api = ApiUtils.getUserAPIService( );
		
		this.btn_registrar = findViewById( R.id.btn_registro_registro );
		this.btn_cancelar = findViewById( R.id.btn_registro_cancelar );
		
		this.edittext_nombres = findViewById( R.id.edittext_registro_nombres );
		this.edittext_correo = findViewById( R.id.edittext_registro_correo );
		this.edittext_password = findViewById( R.id.edittext_registro_password );
		this.edittext_password_verifi = findViewById( R.id.edittext_registro_password_verifi );
		
		this.presenter = new RegistrarmePresenter( this );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		this.btn_registrar.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				
				presenter.clickRegistrme( edittext_nombres.getText( ).toString( ).trim(),
				                          edittext_correo.getText( ).toString( ).trim(),
				                          edittext_password.getText( ).toString( ).trim(),
				                          edittext_password_verifi.getText( ).toString( ).trim() );
				
			}
		} );
		
		this.btn_cancelar.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				clickCancel( );
			}
		} );
	}
	
	@Override
	public void redirectToLogin( ) {
		startActivity( new Intent( this, LoginActivity.class ) );
		finish( );
	}
	
	@Override
	public void showBienvenida( String nombre ) {
		Utilidades.printToast( this, "Bienvenido " + nombre );
	}
	
	@Override
	public void showError( String txt ) {
		Utilidades.printToast( this, txt );
	}
	
	@Override
	public void clickCancel( ) {
		startActivity( new Intent( this_context, MainActivity.class ) );
		finish( );
	}
}
