package com.fcfm.movilesproyect.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fcfm.movilesproyect.R;

public class MainActivity extends AppCompatActivity {
	
	private Button btn_login;
	private Button btn_registrarme;
	
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		this.btn_login = findViewById( R.id.btn_main_login );
		this.btn_registrarme = findViewById( R.id.btn_main_registrarme );
	}
	
	@Override protected void onStart( ) {
		super.onStart( );
		
		this.btn_login.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
			
			}
		} );
		
		this.btn_registrarme.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
			
			}
		} );
	}
}
