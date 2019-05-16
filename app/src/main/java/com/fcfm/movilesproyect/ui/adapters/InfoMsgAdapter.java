package com.fcfm.movilesproyect.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.models.InfoMessages;

import java.util.ArrayList;
import java.util.List;

public class InfoMsgAdapter extends RecyclerView.Adapter< InfoMsgAdapter.ViewHolder > {
	
	private List< InfoMessages > msg;
	private int                  layout;
	private OnItemClickListener  listener;
	
	public InfoMsgAdapter( OnItemClickListener listener ) {
		
		this.msg = new ArrayList< InfoMessages >( );
		this.layout = R.layout.recycler_view_info_msg;
		this.listener = listener;
	}
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
		View v = LayoutInflater.from( viewGroup.getContext( ) )
		                       .inflate( this.layout, viewGroup, false );
		ViewHolder vh = new ViewHolder( v );
		
		return vh;
	}
	
	@Override
	public void onBindViewHolder( @NonNull InfoMsgAdapter.ViewHolder viewHolder, int i ) {
		viewHolder.bind( msg.get( i ), listener );
	}
	
	@Override
	public int getItemCount( ) {
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
				@Override
				public void onClick( View v ) {
					listener.onItemClick( msg, getAdapterPosition( ) );
				}
			} );
		}
	}
	
	public interface OnItemClickListener {
		void onItemClick( InfoMessages msg, int posicion );
	}
	
	public void setMsg( List< InfoMessages > msg ) {
		this.msg = msg;
		this.notifyDataSetChanged( );
	}
}
