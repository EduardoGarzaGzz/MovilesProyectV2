package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.ILoginMVP;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.presenter.LoginPresenter;


import java.util.Date;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements ILoginMVP.View {
	
	private ILoginMVP.Presenter presenter;
	
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
		
		
		this.btn_login = findViewById( R.id.btn_login_login );
		this.btn_cancelar = findViewById( R.id.btn_login_cancelar );
		
		this.edittext_cuenta = findViewById( R.id.edittext_login_cuenta );
		this.edittext_password = findViewById( R.id.edittext_login_password );
		
		this.check_recuerdame = findViewById( R.id.check_login_recuerdame );
		
		this.presenter = new LoginPresenter( this );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		this.checkRecuerdame( );
		
		this.btn_login.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				
				presenter.clickLogin( edittext_cuenta.getText( ).toString( ).trim( ),
				                      edittext_password.getText( ).toString( ).trim( ) );

			}
		} );
		
		this.btn_cancelar.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				clickCancelar( );
			}
		} );
	}
	
	public void checkRecuerdame( ) {
		
		SharedPreferences prefs = getSharedPreferences( "recuerdame", Context.MODE_PRIVATE );
		
		if ( Objects.equals( prefs.getString( "cuenta", "" ), "" ) ) edittext_cuenta.setText( "" );
		else {
			edittext_cuenta.setText( prefs.getString( "cuenta", "" ) );
			check_recuerdame.setChecked( true );
		}
		
		
	}
	
	@Override
	public void setRecuerdame( ) {
		if ( ! check_recuerdame.isChecked( ) ) return;
		
		Utilidades.setRecuerdame( this, edittext_cuenta.getText( ).toString( ).trim( ) );
	}
	
	@Override
	public void setLoginExitoso( Long id ) {
		SharedPreferences prefs = getSharedPreferences( "info_user", Context.MODE_PRIVATE );
		SharedPreferences.Editor edit_prefs = prefs.edit( );
		
		edit_prefs.putString( "login_status", "true" );
		edit_prefs.putString( "login_fecha", new Date( ).toString( ) );
		edit_prefs.putString( "id_user", String.valueOf( id ) );
		edit_prefs.apply( );
		
		Utilidades.printLog( "Login exitoso a las: " + prefs.getString( "login_fecha", "NULL" ) );
	}
	
	@Override
	public void showGoodLogin( ) {
		Utilidades.printToastSuccess( this, "Bienvenido " + User.getUser_active( ).getNombres( ) );
	}
	
	@Override
	public void showError( String txt ) {
		Utilidades.printToastSuccess( this, txt );
	}
	
	@Override
	public void clickCancelar( ) {
		startActivity( new Intent( this, MainActivity.class ) );
		finish( );
	}
	
	@Override
	public void redirecToDashbord( ) {
		startActivity( new Intent( this, DashbordActivity.class ) );
		finish( );
	}
}
