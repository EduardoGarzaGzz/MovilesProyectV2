package com.fcfm.movilesproyect.presenter.interfaces;

import com.fcfm.movilesproyect.db.models.User;

public interface ILoginMVP {
	
	interface View {
		void setLoginExitoso( Long id );
		
		void setRecuerdame( );
		
		void showGoodLogin( );
		
		void showError( String txt );
		
		void clickCancelar( );
		
		void redirecToDashbord( );
	}
	
	interface Presenter {
		void clickLogin( String cuenta, String password );
	}
	
	interface Model {
		void setUserActivo( User user );
	}
	
}
