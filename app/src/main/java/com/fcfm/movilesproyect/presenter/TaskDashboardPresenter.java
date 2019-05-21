package com.fcfm.movilesproyect.presenter;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.TaskEntity;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.db.viewmodel.TaskViewModel;
import com.fcfm.movilesproyect.presenter.interfaces.ITaskDashboardMVP;
import com.fcfm.movilesproyect.ui.adapters.ListTaskAdapter;
import com.fcfm.movilesproyect.ui.fragments.DatePickerFragment;
import com.fcfm.movilesproyect.ui.fragments.FormTaskFragment;
import com.fcfm.movilesproyect.ui.fragments.ListaTaskFragment;

import java.util.List;

public class TaskDashboardPresenter implements ITaskDashboardMVP.Presenter {
	
	private TaskViewModel model;
	
	private ITaskDashboardMVP.View view;
	private ITaskDashboardMVP.View.ListTask listTask;
	private ITaskDashboardMVP.View.FormTask formTask;
	
	public TaskDashboardPresenter( ITaskDashboardMVP.View view ) {
		this.view = view;
	}
	
	@Override
	public void setViewList( ITaskDashboardMVP.View.ListTask viewList ) {
		this.listTask = viewList;
	}
	
	@Override
	public void setViewForm( ITaskDashboardMVP.View.FormTask viewForm ) {
		this.formTask = viewForm;
	}
	
	@Override
	public void initDashboard( FragmentManager fm ) {
		
		model = ViewModelProviders.of( view.getActivity( ) ).get( TaskViewModel.class );
		model.getAllProjectByUserId( this.view.getContext( ), User.getUser_active( ).getId( ) );
		
		Fragment f = Utilidades.changeFragment( fm, R.id.frag_dashbord_task_content,
				new ListaTaskFragment( ) );
		( ( ListaTaskFragment ) f ).setPresenter( this );
	}
	
	@Override
	public void initListTask( RecyclerView recycler_view, final RecyclerView.Adapter adapter,
	                          RecyclerView.LayoutManager layout_manager ) {
		
		Utilidades.setToolbar( this.view.getActivity( ),
				"Tareas de " + User.getUser_active( ).getNombres( ) );
		
		recycler_view.setHasFixedSize( true );
		recycler_view.setItemAnimator( new DefaultItemAnimator( ) );
		recycler_view.setLayoutManager( layout_manager );
		recycler_view.setAdapter( adapter );
		
		this.model = ViewModelProviders.of( this.view.getActivity( ) ).get( TaskViewModel.class );
		
		this.listTask.getFragment( ).getNo_hay( ).setText( "Buscando tareas" );
		
		this.model.getAllTaskByUserId( ).observe( this.view.getActivity( ), new Observer< List< TaskEntity > >( ) {
			@Override
			public void onChanged( @Nullable List< TaskEntity > taskEntities ) {
				if ( taskEntities.size( ) > 0 ) {
					listTask.getFragment( ).getNo_hay( ).setText( "" );
				} else {
					listTask.getFragment( ).getNo_hay( ).setText( "No hay tareas" );
				}
				
				( ( ListTaskAdapter ) adapter ).setListTask( taskEntities );
			}
		} );
	}
	
	@Override
	public void initFormTask( FormTaskFragment fragment ) {
		
		ArrayAdapter< CharSequence > adapterUrgency = ArrayAdapter
				.createFromResource( this.view.getContext( ), R.array.urgency, android.R.layout.simple_spinner_item );
		adapterUrgency.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		fragment.getUrgency( ).setAdapter( adapterUrgency );
		fragment.getUrgency( ).setOnItemSelectedListener( this.selectItemUrgency( ) );
		
		ArrayAdapter< Project > adapterProjects =
				new ArrayAdapter< Project >( this.view.getContext( ), android.R.layout.simple_spinner_item,
						Project.projects );
		adapterProjects.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		fragment.getProject( ).setAdapter( adapterProjects );
		fragment.getProject( ).setOnItemSelectedListener( this.selectItemProject( ) );
		
		// TODO arreglar esto
		if ( ! fragment.getStatus( ) ) {
			int pos = adapterUrgency.getPosition( fragment.getTask( ).urgency );
			fragment.getUrgency( ).setSelection( pos );
			
			pos = adapterProjects.getPosition( Project.getProjectById( fragment.getTask( ).projectId ) );
			fragment.getProject( ).setSelection( pos );
		}
		
		
		fragment.getDateFinal( ).setOnClickListener( this.clickFinalDateTask( fragment ) );
		
	}
	
	@Override
	public DrawerLayout.DrawerListener clickDrawerListener( ) {
		return new DrawerLayout.DrawerListener( ) {
			@Override
			public void onDrawerSlide( @NonNull View view, float v ) {
			
			}
			
			@Override
			public void onDrawerOpened( @NonNull View view ) {
			
			}
			
			@Override
			public void onDrawerClosed( @NonNull View view ) {
			
			}
			
			@Override
			public void onDrawerStateChanged( int i ) {
			
			}
		};
	}
	
	@Override
	public NavigationView.OnNavigationItemSelectedListener clickNavigationView( ) {
		return new NavigationView.OnNavigationItemSelectedListener( ) {
			@Override
			public boolean onNavigationItemSelected( @NonNull MenuItem menuItem ) {
				if ( R.id.menu_task == menuItem.getItemId( ) ) {
					Utilidades.printToastInfo( view.getContext( ), "Ya estas en tareas" );
				} else {
					Utilidades.openActivityWithItemId( view.getActivity( ), menuItem.getItemId( ) );
				}
				
				return true;
			}
		};
	}
	
	@Override
	public View.OnClickListener clickAgregarTask( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_task_content, new FormTaskFragment( ) );
				( ( FormTaskFragment ) f ).setPresenter( view.getPresenter( ) );
				( ( FormTaskFragment ) f ).setStatus( true );
				
			}
		};
	}
	
	@Override
	public ListTaskAdapter.OnItemClickListener clickItemListTask( ) {
		return new ListTaskAdapter.OnItemClickListener( ) {
			@Override
			public void onItemClick( TaskEntity entity, int position ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_task_content, new FormTaskFragment( ) );
				( ( FormTaskFragment ) f ).setPresenter( view.getPresenter( ) );
				( ( FormTaskFragment ) f ).setStatus( false );
				( ( FormTaskFragment ) f ).setTask( entity );
				Utilidades.printLog( entity.toString( ) );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickFormActionTask( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				
				model = ViewModelProviders.of( view.getActivity( ) ).get( TaskViewModel.class );
				
				if ( formTask.getStatus( ) ) {
					model.newTask( view.getContext( ), formTask.getTask( ), formTask.getTask( ).projectId );
				} else {
					model.updateTask( view.getContext( ), formTask.getTask( ) );
				}
				
			}
		};
	}
	
	@Override
	public View.OnClickListener clickFormCancelTask( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_task_content, new ListaTaskFragment( ) );
				( ( ListaTaskFragment ) f ).setPresenter( view.getPresenter( ) );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickFinalDateTask( final FormTaskFragment fragment ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				final DatePickerFragment dialog = DatePickerFragment.newInstance(
						new DatePickerDialog.OnDateSetListener( ) {
							@Override
							public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {
								final String selectedDate = dayOfMonth + " / " + ( month + 1 ) + " / " + year;
								fragment.getDateFinal( ).setText( selectedDate );
							}
						} );
				
				dialog.show( view.getActivity( ).getSupportFragmentManager( ), "datePicker" );
			}
		};
	}
	
	@Override
	public AdapterView.OnItemSelectedListener selectItemUrgency( ) {
		return new AdapterView.OnItemSelectedListener( ) {
			@Override
			public void onItemSelected( AdapterView< ? > parent, View view, int position, long id ) {
				String txt = parent.getItemAtPosition( position ).toString( );
				Utilidades.printToastInfo( view.getContext( ), "Esta seleccionado: " + txt );
			}
			
			@Override
			public void onNothingSelected( AdapterView< ? > parent ) {
			
			}
		};
	}
	
	@Override
	public AdapterView.OnItemSelectedListener selectItemProject( ) {
		return new AdapterView.OnItemSelectedListener( ) {
			@Override
			public void onItemSelected( AdapterView< ? > parent, View view, int position, long id ) {
				Project tmp = ( Project ) parent.getItemAtPosition( position );
				Utilidades.printToastInfo( view.getContext( ), "Esta seleccionado: " + tmp.title );
				formTask.getTask( ).projectId = tmp.id;
			}
			
			@Override
			public void onNothingSelected( AdapterView< ? > parent ) {
			
			}
		};
	}
}
