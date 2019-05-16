package com.fcfm.movilesproyect.interfaces;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface IProjectDashboard {
	
	interface View {
		
		AppCompatActivity getActivity( );
		
		Context getContext( );
		
		IProjectDashboard.Presenter getPresenter( );
		
		interface List {
			
			RecyclerView getLista( );
			
			void setPresenter( IProjectDashboard.Presenter presenter );
			
		}
		
		interface Form {
			
			void setStatus( boolean status );
			
			boolean getStatus( );
			
			void setPresenter( IProjectDashboard.Presenter presenter );
			
		}
		
	}
	
	interface Presenter {
		
		void init( );
		
		void setViewList( IProjectDashboard.View.List view_list );
		
		void setViewForm( IProjectDashboard.View.Form view_form );
		
		void initViewList( );
		
		void initViewForm( );
		
		android.view.View.OnClickListener clickAgregarProyecto( );
		
		android.view.View.OnClickListener clickActionProyecto( );
		
		android.view.View.OnClickListener clickCacelarProyecto( );
		
		DrawerLayout.DrawerListener clickDrawerListener( );
		
		NavigationView.OnNavigationItemSelectedListener clickNavigationView( );
	}
	
	interface Model {
	
	}
	
}
