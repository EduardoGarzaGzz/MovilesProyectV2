package com.fcfm.movilesproyect.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.apis.InfoMsgAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.models.InfoMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoMsgAdapter extends RecyclerView.Adapter< InfoMsgAdapter.ViewHolder > {
	
	private List< InfoMessages > msg;
	private int                  layout;
	private OnItemClickListener  listener;
	
	public InfoMsgAdapter( long id, int layout,
						   OnItemClickListener listener ) {
		
		InfoMsgAPIService api = ApiUtils.getInfoMsgApiService( );
		
		api.getUserAllInfoMsg( String.valueOf( id ) )
		   .enqueue(
				   new Callback< InfoMessages[] >( ) {
					   @Override public void onResponse( Call< InfoMessages[] > call,
														 Response< InfoMessages[] > response ) {
						
						   if ( response.isSuccessful( ) ) {
							   msg = Arrays.asList( response.body( ) );
							   notifyDataSetChanged( );
						   }
						   Log.e( "MY APP LOG",
								  "Response peticion (getUserAllInfoMsg) " + response.code( ) );
					   }
					
					   @Override public void onFailure( Call< InfoMessages[] > call, Throwable t ) {
						   Log.e( "MY APP LOG", "Peticion FAIL" );
						   Log.e( "MY APP LOG", t.getMessage( ) + " : " + t.toString( ) );
					   }
				   } );
		
		this.msg = new ArrayList< InfoMessages >( );
		this.layout = layout;
		this.listener = listener;
	}
	
	@NonNull @Override public ViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
		View v = LayoutInflater.from( viewGroup.getContext( ) ).inflate( this.layout, viewGroup,
																		 false );
		
		ViewHolder vh = new ViewHolder( v );
		
		return vh;
	}
	
	@Override public void onBindViewHolder( @NonNull ViewHolder viewHolder, int i ) {
		viewHolder.bind( msg.get( i ), listener );
	}
	
	@Override public int getItemCount( ) {
		return this.msg.size( );
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		
		public ImageView img;
		public TextView  text;
		
		public ViewHolder( View v ) {
			super( v );
			
			this.img = ( ImageView ) v.findViewById( R.id.img_rv_info_msg );
			this.text = ( TextView ) v.findViewById( R.id.tv_rv_info_msg );
		}
		
		public void bind( final InfoMessages msg, final OnItemClickListener listener ) {
			this.text.setText( msg.getMessages( ) );
			
			itemView.setOnClickListener( new View.OnClickListener( ) {
				@Override public void onClick( View v ) {
					listener.onItemClick( msg, getAdapterPosition( ) );
				}
			} );
		}
	}
	
	public interface OnItemClickListener {
		void onItemClick( InfoMessages msg, int posicion );
	}
	
}
