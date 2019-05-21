package com.fcfm.movilesproyect.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.models.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class ListTaskAdapter extends RecyclerView.Adapter< ListTaskAdapter.ViewHolder > {
	
	private List< TaskEntity >                  list;
	private int                                 layaout;
	private ListTaskAdapter.OnItemClickListener listener;
	
	public ListTaskAdapter( int layaout, OnItemClickListener listener ) {
		this.layaout = layaout;
		this.listener = listener;
		this.list = new ArrayList< TaskEntity >( );
	}
	
	@NonNull
	@Override
	public ListTaskAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
		View view = LayoutInflater.from( viewGroup.getContext( ) )
		                          .inflate( this.layaout, viewGroup, false );
		return new ViewHolder( view );
	}
	
	@Override
	public void onBindViewHolder( @NonNull ListTaskAdapter.ViewHolder viewHolder, int i ) {
		viewHolder.bind( this.list.get( i ), this.listener );
	}
	
	@Override
	public int getItemCount( ) {
		return this.list.size( );
	}
	
	public interface OnItemClickListener {
		void onItemClick( TaskEntity entity, int position );
	}
	
	public void setListTask( List< TaskEntity > list ) {
		this.list = list;
		this.notifyDataSetChanged( );
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		
		private LinearLayout llTask;
		private TextView     tvTitle;
		private TextView     tvUsername;
		private TextView     tvDescription;
		
		public ViewHolder( @NonNull View v ) {
			super( v );
			
			this.llTask = v.findViewById( R.id.ll_recycle_task_layaouts );
			this.tvTitle = v.findViewById( R.id.tv_rv_task_title );
			this.tvUsername = v.findViewById( R.id.tv_rv_task_username );
			this.tvDescription = v.findViewById( R.id.tv_rv_task_descripion );
		}
		
		public void bind( final TaskEntity entity,
		                  final ListTaskAdapter.OnItemClickListener listener ) {
			
			this.tvTitle.setText( entity.title );
			this.tvUsername.setText( "TOP level: " + entity.urgency );
			this.tvDescription.setText( entity.description );
			
			this.llTask.setOnClickListener( new View.OnClickListener( ) {
				@Override
				public void onClick( View v ) {
					listener.onItemClick( entity, getAdapterPosition( ) );
				}
			} );
		}
	}
}
