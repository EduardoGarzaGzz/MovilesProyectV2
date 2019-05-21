package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.fcfm.movilesproyect.R;

import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IMainMVP;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.presenter.MainPresenter;


public class MainActivity extends AppCompatActivity implements IMainMVP.View {
	
	private IMainMVP.Presenter presenter;
	
	private Button  btn_login;
	private Button  btn_registrarme;
	private Context this_context;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		this.btn_login = findViewById( R.id.btn_main_login );
		this.btn_registrarme = findViewById( R.id.btn_main_registrarme );
		
		this.presenter = new MainPresenter( this );
		this.presenter.init( );
		
		this.this_context = this;
		
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		this.btn_login.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				startActivity( new Intent( this_context, LoginActivity.class ) );
			}
		} );
		
		this.btn_registrarme.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				startActivity( new Intent( this_context, RegistrarmeActivity.class ) );
			}
		} );
		
		
	}
	
	@Override
	public Context getActivity( ) {
		return this;
	}
	
	@Override
	public void showBienvenida( ) {
		Utilidades.printToastSuccess( this, "Bienvenido " + User.getUser_active( ).getNombres( ) );
	}
	
	
	@Override
	public void redirectDashbord( ) {
		startActivity( new Intent( this, DashbordActivity.class ) );
		finish( );
	}
}
