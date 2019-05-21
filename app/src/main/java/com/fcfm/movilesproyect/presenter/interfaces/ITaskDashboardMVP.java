package com.fcfm.movilesproyect.presenter.interfaces;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import android.widget.AdapterView;
import com.fcfm.movilesproyect.db.models.TaskEntity;
import com.fcfm.movilesproyect.ui.adapters.ListTaskAdapter;
import com.fcfm.movilesproyect.ui.fragments.FormTaskFragment;
import com.fcfm.movilesproyect.ui.fragments.ListaTaskFragment;

public interface ITaskDashboardMVP {
	
	interface View {
		AppCompatActivity getActivity( );
		
		Context getContext( );
		
		ITaskDashboardMVP.Presenter getPresenter( );
		
		interface ListTask {
			
			void setPresenter( ITaskDashboardMVP.Presenter presenter );
			
			ListaTaskFragment getFragment( );
		}
		
		interface FormTask {
			
			FormTaskFragment getFormTaskFragment( );
			
			void setStatus( boolean status );
			
			boolean getStatus( );
			
			void setPresenter( ITaskDashboardMVP.Presenter presenter );
			
			void setTask( TaskEntity task );
			
			TaskEntity getTask( );
			
		}
	}
	
	interface Presenter {
		
		void setViewList( ITaskDashboardMVP.View.ListTask viewList );
		
		void setViewForm( ITaskDashboardMVP.View.FormTask viewForm );
		
		void initDashboard( FragmentManager fm );
		
		void initListTask( RecyclerView recycler_view, RecyclerView.Adapter adapter,
		                   RecyclerView.LayoutManager layout_manager );
		
		void initFormTask( FormTaskFragment fragment );
		
		DrawerLayout.DrawerListener clickDrawerListener( );
		
		NavigationView.OnNavigationItemSelectedListener clickNavigationView( );
		
		android.view.View.OnClickListener clickAgregarTask( );
		
		ListTaskAdapter.OnItemClickListener clickItemListTask( );
		
		android.view.View.OnClickListener clickFormActionTask( );
		
		android.view.View.OnClickListener clickFormCancelTask( );
		
		android.view.View.OnClickListener clickFinalDateTask( FormTaskFragment fragment );
		
		AdapterView.OnItemSelectedListener selectItemUrgency( );
		
		AdapterView.OnItemSelectedListener selectItemProject( );
	}
	
	interface Model {
	
	}
	
}
/*
 * ANDROID_HOME  C:\Users\Sheimus\AppData\Local\Android\Sdk
 * JAVA_HOME  C:\Program Files\Java\jdk1.8.0_191
 * */