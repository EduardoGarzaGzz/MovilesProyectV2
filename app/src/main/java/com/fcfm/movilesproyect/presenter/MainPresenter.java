package com.fcfm.movilesproyect.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.fcfm.movilesproyect.db.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IMainMVP;
import com.fcfm.movilesproyect.db.models.User;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainMVP.Presenter {
	
	private IMainMVP.View  view;
	private IMainMVP.Model model;
	
	private UserAPIService service;
	
	public MainPresenter( IMainMVP.View view ) {
		this.view = view;
		this.model = new User( );
	}
	
	private boolean checkLastLogin( SharedPreferences preferences ) {
		
		Date login, hoy = null;
		int  diferencia = 0;
		
		try {
			login = new Date( preferences.getString( "login_fecha", "NULL" ) );
			hoy = new Date( );
			
			diferencia = ( int ) ( ( hoy.getTime( ) - login.getTime( ) ) / 1000 );
			
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
			
			Utilidades.printLog(
					"Hay " + dias + " dias, " + horas + " horas, " + minutos + " minutos y " +
					diferencia + " segundos de diferencia" );
			
			if ( minutos > 30 ) {
				Utilidades.printLog( "Tiene que volver al log" );
				return false;
			}
			
		}catch ( Exception e ) {
			e.printStackTrace( );
		}
		
		return true;
	}
	
	@Override
	public void init( ) {
		
		this.service = ApiUtils.getUserAPIService( );
		
		this.checkLogin( );
	}
	
	@Override
	public void checkLogin( ) {
		final Context ctx = view.getActivity( );
		
		SharedPreferences preferences = Utilidades.getSharedPreferencesInfoUser( ctx );
		
		if ( ! preferences.getString( "login_status", "NULL" ).equals( "true" ) ) return;
		
		if ( preferences.getString( "login_fecha", "NULL" ).equals( "NULL" ) ) return;
		else {
			if ( ! this.checkLastLogin( preferences ) ) return;
		}
		
		this.service.getUser( preferences.getString( "id_user", "0" ) )
		            .enqueue( new Callback< User >( ) {
			            @Override
			            public void onResponse( Call< User > call, Response< User > response ) {
				
				            if ( response.isSuccessful( ) ) {
					            Utilidades.printResponseBody( response );
					
					            User tmp_user = response.body( );
					
					            if ( tmp_user.getId( ) != 0 &&
					                 ! tmp_user.getCorreo( ).equals( "null" ) ) {
						
						            model.setUserActivo( tmp_user );
						            view.showBienvenida( );
						            view.redirectDashbord( );
					            }
					
				            }
				
			            }
			
			            @Override
			            public void onFailure( Call< User > call, Throwable t ) {
				            Utilidades.failPeticionApi( t );
			            }
		            } );
		
	}
}
