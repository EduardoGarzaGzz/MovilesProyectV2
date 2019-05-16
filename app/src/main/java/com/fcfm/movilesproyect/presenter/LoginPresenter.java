package com.fcfm.movilesproyect.presenter;

import com.fcfm.movilesproyect.db.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.ILoginMVP;
import com.fcfm.movilesproyect.db.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginMVP.Presenter {
	
	private ILoginMVP.View  view;
	private ILoginMVP.Model model;
	
	private UserAPIService api;
	
	public LoginPresenter( ILoginMVP.View view ) {
		this.view = view;
		this.model = new User( );
	}
	
	@Override
	public void clickLogin( String cuenta, String password ) {
		
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
		                      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern       pattern = Pattern.compile( emailPattern );
		final Matcher matcher = pattern.matcher( cuenta );
		
		User user = new User( 0, cuenta, "", "", "", password, cuenta );
		
		this.api = ApiUtils.getUserAPIService( );
		
		if ( matcher.matches( ) ) {
			user.setCorreo( cuenta );
		}
		
		this.api.login( user ).enqueue( new Callback< User >( ) {
			@Override
			public void onResponse( Call< User > call, Response< User > response ) {
				
				Utilidades.printResponseCode( response );
				
				if ( response.isSuccessful( ) ) {
					
					Utilidades.printResponseBody( response );
					
					User tmp_user = response.body( );
					
					if ( tmp_user.getId( ) != 0 && ! tmp_user.getCorreo( ).equals( "null" ) ) {
						
						view.setRecuerdame();
						view.setLoginExitoso( tmp_user.getId() );
						
						model.setUserActivo( tmp_user );
						
						view.showGoodLogin();
						
						view.redirecToDashbord();
					}
					else {
						view.showError( "Error vuelva a intentarlo porfavor" );
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
