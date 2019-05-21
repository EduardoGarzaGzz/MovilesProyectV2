package com.fcfm.movilesproyect.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.models.Project;

import java.util.ArrayList;
import java.util.List;

public class ListProjectsAdapter extends RecyclerView.Adapter< ListProjectsAdapter.ViewHolder > {
	
	private List< Project >     projects;
	private int                 layaout;
	private OnItemClickListener listener;
	
	public ListProjectsAdapter( OnItemClickListener listener ) {
		this.listener = listener;
		this.projects = new ArrayList< Project >( );
		this.layaout = R.layout.recycler_view_list_proyects;
	}
	
	@NonNull
	@Override
	public ListProjectsAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup,
	                                                          int i ) {
		View v = LayoutInflater.from( viewGroup.getContext( ) )
		                       .inflate( this.layaout, viewGroup, false );
		return new ViewHolder( v );
	}
	
	@Override
	public void onBindViewHolder( @NonNull ListProjectsAdapter.ViewHolder viewHolder, int i ) {
		viewHolder.bind( projects.get( i ), listener );
	}
	
	@Override
	public int getItemCount( ) {
		return this.projects.size( );
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		
		private LinearLayout content;
		
		private TextView title;
		private TextView description;
		private TextView create_at;
		private TextView update_at;
		
		public ViewHolder( @NonNull View v ) {
			super( v );
			
			this.content = v.findViewById( R.id.ll_recycle_layaouts );
			
			this.title = v.findViewById( R.id.tx_recycle_list_proyects_title );
			this.description = v.findViewById( R.id.tx_recycle_list_proyects_description );
			this.create_at = v.findViewById( R.id.tx_recycle_list_proyects_create );
			this.update_at = v.findViewById( R.id.tx_recycle_list_proyects_update );
		}
		
		public void bind( final Project project, final OnItemClickListener listener ) {
			
			Utilidades.printLog( "in bind:  " + project.toString( ) );
			
			this.title.setText( project.getTitle( ) );
			this.description.setText( project.getDescription( ) );
			
			if ( project.create_at != null ) {
				this.create_at.setText( project.getCreate_at( ).toString( ) );
				
				if ( project.getUpdate_at( ) == null )
					this.update_at.setText( project.getCreate_at( ).toString( ) );
				else this.update_at.setText( project.getUpdate_at( ).toString( ) );
			} else {
				this.create_at.setText( "No registrada" );
				this.update_at.setText( "No registrada" );
			}
			
			
			this.content.setOnClickListener( new View.OnClickListener( ) {
				@Override
				public void onClick( View v ) {
					listener.onItemClick( project, getAdapterPosition( ) );
				}
			} );
		}
	}
	
	public interface OnItemClickListener {
		void onItemClick( Project project, int position );
	}
	
	public void setListProjects( List< Project > projects ) {
		this.projects = projects;
		this.notifyDataSetChanged( );
	}
}
