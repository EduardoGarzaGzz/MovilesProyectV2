package com.fcfm.movilesproyect.presenter.interfaces;

import android.content.Context;

import com.fcfm.movilesproyect.db.models.User;

public interface IMainMVP {
	
	interface View {
		Context getActivity( );
		
		void showBienvenida();
		
		void redirectDashbord();
	}
	
	interface Presenter {
		void init( );
		
		void checkLogin( );
		
	}
	
	interface Model {
		void setUserActivo( User user );
	}
	
}
