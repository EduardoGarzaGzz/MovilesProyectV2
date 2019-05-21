package com.fcfm.movilesproyect.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.TaskEntity;
import com.fcfm.movilesproyect.presenter.interfaces.ITaskDashboardMVP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormTaskFragment extends Fragment implements ITaskDashboardMVP.View.FormTask {
	
	
	private ITaskDashboardMVP.Presenter presenter;
	
	private boolean status;
	private TaskEntity taskEntity;
	
	private EditText title;
	private EditText description;
	private EditText dateFinal;
	
	private Spinner project;
	private Spinner urgency;
	
	private Button btnAction;
	private Button btnCancel;
	
	public FormTaskFragment( ) {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View view = inflater.inflate( R.layout.fragment_form_task, container, false );
		
		this.title = view.findViewById( R.id.txt_frag_form_task_title );
		this.description = view.findViewById( R.id.txt_frag_form_task_description );
		this.dateFinal = view.findViewById( R.id.date_frag_form_task_final );
		this.project = view.findViewById( R.id.sp_frag_form_task_project );
		this.urgency = view.findViewById( R.id.sp_frag_form_task_urgency );
		this.btnAction = view.findViewById( R.id.btn_frag_form_task_action );
		this.btnCancel = view.findViewById( R.id.btn_frag_form_task_cancel );
		
		if ( this.status ) {
			this.btnAction.setText( "Agregar" );
		} else {
			this.title.setText( this.taskEntity.title );
			this.description.setText( this.taskEntity.description );
			this.btnAction.setText( "Actualizar" );
		}
		
		this.presenter.initFormTask( this );
		this.presenter.setViewForm( this );
		
		return view;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btnAction.setOnClickListener( this.presenter.clickFormActionTask( ) );
		this.btnCancel.setOnClickListener( this.presenter.clickFormCancelTask( ) );
	}
	
	@Override
	public FormTaskFragment getFormTaskFragment( ) {
		return this;
	}
	
	@Override
	public void setStatus( boolean status ) {
		this.status = status;
	}
	
	@Override
	public boolean getStatus( ) {
		return this.status;
	}
	
	@Override
	public void setPresenter( ITaskDashboardMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
	
	@Override
	public void setTask( TaskEntity task ) {
		this.taskEntity = task;
	}
	
	@Override
	public TaskEntity getTask( ) {
		if ( this.taskEntity == null ) this.taskEntity = new TaskEntity( );
		
		this.taskEntity.title = this.title.getText( ).toString( ).trim( );
		this.taskEntity.description = this.description.getText( ).toString( ).trim( );
		this.taskEntity.projectId = ( ( Project ) this.project.getSelectedItem( ) ).id;
		this.taskEntity.urgency = this.urgency.getSelectedItem( ).toString( );
		try {
			if ( this.dateFinal.getText( ).toString( ).equals( "" ) ) {
				this.taskEntity.final_date = null;
			} else {
				this.taskEntity.final_date =
						new Date( String.valueOf(
								new SimpleDateFormat( "dd/MM/yyyy" ).parse( this.dateFinal.getText( ).toString( ) ) ) );
			}
		} catch ( ParseException e ) {
			e.printStackTrace( );
		}
		
		this.taskEntity.create_at = null;
		this.taskEntity.update_at = null;
		return this.taskEntity;
	}
	
	public EditText getTitle( ) {
		return title;
	}
	
	public void setTitle( EditText title ) {
		this.title = title;
	}
	
	public EditText getDescription( ) {
		return description;
	}
	
	public void setDescription( EditText description ) {
		this.description = description;
	}
	
	public EditText getDateFinal( ) {
		return dateFinal;
	}
	
	public void setDateFinal( EditText dateFinal ) {
		this.dateFinal = dateFinal;
	}
	
	public Spinner getProject( ) {
		return project;
	}
	
	public void setProject( Spinner project ) {
		this.project = project;
	}
	
	public Spinner getUrgency( ) {
		return urgency;
	}
	
	public void setUrgency( Spinner urgency ) {
		this.urgency = urgency;
	}
	
	public Button getBtnAction( ) {
		return btnAction;
	}
	
	public void setBtnAction( Button btnAction ) {
		this.btnAction = btnAction;
	}
	
	public Button getBtnCancel( ) {
		return btnCancel;
	}
	
	public void setBtnCancel( Button btnCancel ) {
		this.btnCancel = btnCancel;
	}
}
