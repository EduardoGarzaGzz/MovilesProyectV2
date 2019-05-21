package com.fcfm.movilesproyect.presenter.interfaces;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.fcfm.movilesproyect.ui.fragments.ListaCitasFragment;
import com.fcfm.movilesproyect.ui.fragments.RegistrarCitsaFragment;

public interface ICitaDashboardMVP {
	
	interface View {
		
		AppCompatActivity getActivity( );
		
		Context getContext( );
		
		interface FormCita {
			
			RegistrarCitsaFragment getFragment( );
			
			void setPresenter( ICitaDashboardMVP.Presenter presenter );
			
			void setStatus( boolean status );
			
			boolean getStatus( );
		}
		
		interface ListCita {
			
			ListaCitasFragment getFragment( );
			
			void setPresenter( ICitaDashboardMVP.Presenter presenter );
		}
	}
	
	interface Presenter {
		
		void initCitaDashboard( );
		
		void initListaCita( );
		
		void initFormCita( );
		
		android.view.View.OnClickListener clickAgregarCita( );
		
		android.view.View.OnClickListener clickActionCita( );
		
		android.view.View.OnClickListener clickCancelCita( );
	}
	
	interface Model {
	
	}
}
