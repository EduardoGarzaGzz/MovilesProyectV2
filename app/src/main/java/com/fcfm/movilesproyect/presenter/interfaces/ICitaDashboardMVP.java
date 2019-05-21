package com.fcfm.movilesproyect.presenter.interfaces;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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
		
		DrawerLayout.DrawerListener clickDrawerListener( );
		
		NavigationView.OnNavigationItemSelectedListener clickNavigationView( );
		
		void initListaCita( );
		
		void initFormCita( );
		
		android.view.View.OnClickListener clickAgregarCita( );
		
		android.view.View.OnClickListener clickActionCita( );
		
		android.view.View.OnClickListener clickCancelCita( );
		
		android.view.View.OnClickListener clickDate( final RegistrarCitsaFragment fragment );
	}
	
	interface Model {
	
	}
}
