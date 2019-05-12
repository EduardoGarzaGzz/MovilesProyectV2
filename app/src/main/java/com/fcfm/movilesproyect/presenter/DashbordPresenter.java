package com.fcfm.movilesproyect.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.adapters.InfoMsgAdapter;
import com.fcfm.movilesproyect.apis.InfoMsgAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.interfaces.IDashbordMVP;
import com.fcfm.movilesproyect.models.InfoMessages;
import com.fcfm.movilesproyect.models.User;
import com.fcfm.movilesproyect.views.activitys.EditUserActivity;
import com.fcfm.movilesproyect.views.activitys.MainActivity;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashbordPresenter implements IDashbordMVP.Presenter {
	
	private IDashbordMVP.View         view;
	private IDashbordMVP.View.InfoMsg info_msg;
	
	public DashbordPresenter( IDashbordMVP.View view ) {
		this.view = view;
	}
	
	@Override
	public void init( ) {
		this.view.setToolbar( );
		this.view.setTabLayaout( );
		
		Utilidades.checkedMenuItem( this.view.getNavigationView( ), 0 );
	}
	
	@Override
	public void initInfoMsg( IDashbordMVP.View.InfoMsg info_msg ) {
		this.info_msg = info_msg;
	}
	
	@Override
	public void clickNavigationItem( int item_id ) {
		
		if ( R.id.menu_dashbord == item_id )
			Utilidades.printToast( this.view.getContext( ), "Ya estas en el dashbord" );
		else Utilidades.openActivityWithItemId( this.view.getActivity( ), item_id );
	}
	
	@Override
	public boolean clickOptionItem( DrawerLayout drawerLayout, int item_id ) {
		switch ( item_id ) {
			case android.R.id.home:
				drawerLayout.openDrawer( GravityCompat.START );
				return true;
		}
		return false;
	}
	
	@Override
	public View.OnClickListener clickMyPerfil( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				view.getActivity().startActivity( new Intent( view.getContext(),
				                                              EditUserActivity.class ) );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickCloseSession( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				
				SharedPreferences preferences = Utilidades.getSharedPreferencesInfoUser(
						view.getContext( ) );
				SharedPreferences.Editor editor = preferences.edit( );
				
				editor.putString( "login_status", "false" );
				editor.putString( "login_fecha", "" );
				editor.putString( "id_user", "0" );
				editor.apply( );
				
				Utilidades.printLog( "La session fue cerrada" );
				
				view.getActivity( )
				    .startActivity( new Intent( view.getContext( ), MainActivity.class ) );
				view.getActivity().finish();
			}
		};
	}
	
	@Override
	public void initInfoMsgRecyclerView( RecyclerView recycler_view, RecyclerView.Adapter adapter,
	                                     RecyclerView.LayoutManager layout_manager ) {
		recycler_view.setHasFixedSize( true );
		recycler_view.setItemAnimator( new DefaultItemAnimator( ) );
		recycler_view.setLayoutManager( layout_manager );
		recycler_view.setAdapter( adapter );
		
		this.setListOfInfoMsg( );
	}
	
	@Override
	public InfoMsgAdapter.OnItemClickListener clickItemInfoMsgAdapter( ) {
		return new InfoMsgAdapter.OnItemClickListener( ) {
			@Override
			public void onItemClick( InfoMessages msg, int posicion ) {
			
			}
		};
	}
	
	@Override
	public void setListOfInfoMsg( ) {
		
		InfoMsgAPIService api = ApiUtils.getInfoMsgApiService( );
		
		api.getUserAllInfoMsg( String.valueOf( User.getUser_active( ).getId( ) ) )
		   .enqueue( new Callback< InfoMessages[] >( ) {
			   @Override
			   public void onResponse( Call< InfoMessages[] > call,
			                           Response< InfoMessages[] > response ) {
				   Utilidades.printResponseCode( response );
				
				   if ( response.isSuccessful( ) ) {
					   Utilidades.printResponseBody( response );
					
					   info_msg.setListOfInfoMsg( Arrays.asList( response.body( ) ) );
				   }
				
			   }
			
			   @Override
			   public void onFailure( Call< InfoMessages[] > call, Throwable t ) {
				   Utilidades.failPeticionApi( t );
			   }
		   } );
		
	}
}