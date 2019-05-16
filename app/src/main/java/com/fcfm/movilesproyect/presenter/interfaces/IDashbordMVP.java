package com.fcfm.movilesproyect.presenter.interfaces;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.fcfm.movilesproyect.ui.adapters.InfoMsgAdapter;
import com.fcfm.movilesproyect.db.models.InfoMessages;

import java.util.List;

public interface IDashbordMVP {
	
	interface View {
		
		AppCompatActivity getActivity( );
		
		NavigationView getNavigationView( );
		
		Context getContext( );
		
		void setToolbar( );
		
		void setTabLayaout( );
		
		interface InfoMsg {
			
			void setListOfInfoMsg( List< InfoMessages > msg );
			
		}
		
		interface Configure {
			
			void setPresenter( IDashbordMVP.Presenter presenter );
			
		}
		
		interface Dashbord {
			
			void setPresenter( IDashbordMVP.Presenter presenter );
			
		}
	}
	
	interface Presenter {
		
		void init( );
		
		void initInfoMsg( IDashbordMVP.View.InfoMsg info_msg );
		
		void initInfoMsgRecyclerView( RecyclerView recycler_view, RecyclerView.Adapter adapter,
		                              RecyclerView.LayoutManager layout_manager );
		
		InfoMsgAdapter.OnItemClickListener clickItemInfoMsgAdapter( );
		
		void setListOfInfoMsg( );
		
		void clickNavigationItem( int item_id );
		
		boolean clickOptionItem( DrawerLayout drawerLayout, int item_id );
		
		android.view.View.OnClickListener clickMyPerfil( );
		
		android.view.View.OnClickListener clickCloseSession( );
		
		android.view.View.OnClickListener clickQuickProjects( );
		
		android.view.View.OnClickListener clickQuickTask( );
		
		android.view.View.OnClickListener clickQuickFriends( );
		
		android.view.View.OnClickListener clickQuickCites( );
	}
	
	interface Model {
	
	}
}
