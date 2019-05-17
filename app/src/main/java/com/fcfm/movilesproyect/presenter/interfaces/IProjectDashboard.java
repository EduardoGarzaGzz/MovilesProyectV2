package com.fcfm.movilesproyect.presenter.interfaces;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.fcfm.movilesproyect.ui.adapters.ListProjectsAdapter;
import com.fcfm.movilesproyect.db.models.Project;

public interface IProjectDashboard {
	
	interface View {
		
		AppCompatActivity getActivity( );
		
		Context getContext( );
		
		IProjectDashboard.Presenter getPresenter( );
		
		interface List {
			
			void setListProjects( java.util.List< Project > projects );
			
			RecyclerView getLista( );
			
			TextView getInfoText( );
			
			void setPresenter( IProjectDashboard.Presenter presenter );
			
		}
		
		interface Form {
			
			void setStatus( boolean status );
			
			boolean getStatus( );
			
			void setPresenter( IProjectDashboard.Presenter presenter );
			
			Project getProject( );
			
			void setProject( Project project );
		}
		
	}
	
	interface Presenter {
		
		void init( );
		
		void setViewList( IProjectDashboard.View.List view_list );
		
		void setViewForm( IProjectDashboard.View.Form view_form );
		
		void initViewList( RecyclerView recycler_view, RecyclerView.Adapter adapter,
		                   RecyclerView.LayoutManager layout_manager );
		
		void initViewForm( );
		
		ListProjectsAdapter.OnItemClickListener clickItemListProject( );
		
		android.view.View.OnClickListener clickAgregarProyecto( );
		
		android.view.View.OnClickListener clickActionProyecto( );
		
		android.view.View.OnClickListener clickCacelarProyecto( );
		
		DrawerLayout.DrawerListener clickDrawerListener( );
		
		NavigationView.OnNavigationItemSelectedListener clickNavigationView( );
	}
	
	interface Model {
	
	}
	
}
