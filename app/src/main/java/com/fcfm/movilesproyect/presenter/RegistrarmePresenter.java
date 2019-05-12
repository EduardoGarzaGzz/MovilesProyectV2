package com.fcfm.movilesproyect.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.fcfm.movilesproyect.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.interfaces.IRegistrarmeMVP;
import com.fcfm.movilesproyect.models.User;
import com.fcfm.movilesproyect.views.activitys.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarmePresenter implements IRegistrarmeMVP.Presenter {
	
	private IRegistrarmeMVP.View view;
	
	private UserAPIService api;
	
	public RegistrarmePresenter( IRegistrarmeMVP.View view ) {
		this.view = view;
	}
	
	@Override
	public void clickRegistrme( final String nombres, String correo, String password,
	                            String password_verifi ) {
		
		this.api = ApiUtils.getUserAPIService( );
		
		if ( nombres.length( ) < 2 ) {
			
			this.view.showError( "Nombre demaciado corto" );
			return;
		}
		
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
		                      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile( emailPattern );
		Matcher matcher = pattern.matcher( correo );
		
		if ( correo.equals( "" ) || ! matcher.matches( ) ) {
			
			this.view.showError( "El correo no es valido" );
			return;
		}
		
		if ( password.length( ) < 1 ) {
			
			this.view.showError( "La contraseña no es valida" );
			return;
		}
		
		if ( ! password.equals( password_verifi ) ) {
			
			this.view.showError( "La contraseña deben ser iguales" );
			return;
		}
		
		User user = new User( 0, "", nombres, "", correo, password, "" );
		user.setUsername( nombres );
		
		this.api.registrarUser( user ).enqueue( new Callback< User >( ) {
			@Override
			public void onResponse( Call< User > call, Response< User > response ) {
				
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					
					Utilidades.printResponseBody( response );
					
					view.showBienvenida( nombres );
					view.redirectToLogin( );
					
				}
				else {
					view.showError( "Error vuelva a intentarlo" );
				}
				
			}
			
			@Override
			public void onFailure( Call< User > call, Throwable t ) {
				Utilidades.failPeticionApi( t );
			}
		} );
	}
	
}
