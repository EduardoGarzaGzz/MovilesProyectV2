package com.fcfm.movilesproyect.presenter.interfaces;

public interface IRegistrarmeMVP {
	
	interface View {
		void redirectToLogin( );
		
		void showBienvenida( String nombre );
		
		void showError( String txt );
		
		void clickCancel();
	}
	
	interface Presenter {
		void clickRegistrme( String nombres, String correo, String password,
		                     String password_verifi );
		
	}
	
}
