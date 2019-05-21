package com.fcfm.movilesproyect.presenter;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.viewmodel.CitaViewModel;
import com.fcfm.movilesproyect.presenter.interfaces.ICitaDashboardMVP;
import com.fcfm.movilesproyect.ui.activitys.CitasDashbordActivity;
import com.fcfm.movilesproyect.ui.fragments.DatePickerFragment;
import com.fcfm.movilesproyect.ui.fragments.ListaCitasFragment;
import com.fcfm.movilesproyect.ui.fragments.RegistrarCitsaFragment;

public class CitaDashboardPresenter implements ICitaDashboardMVP.Presenter {
	
	private ICitaDashboardMVP.View view;
	private ICitaDashboardMVP.View.ListCita listCita;
	private ICitaDashboardMVP.View.FormCita formCita;
	
	private CitaViewModel citaViewModel;
	
	public CitaDashboardPresenter( ICitaDashboardMVP.View view ) {
		this.view = view;
	}
	
	@Override
	public void initCitaDashboard( ) {
		
		Utilidades.setToolbar( this.view.getActivity( ), "Lista de citas" );
		
		Fragment f = Utilidades.changeFragment( this.view.getActivity( ).getSupportFragmentManager( ),
				R.id.frag_dashbord_citas_content, new ListaCitasFragment( ) );
		( ( ListaCitasFragment ) f ).setPresenter( this );
		this.listCita = ( ListaCitasFragment ) f;
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
				if ( R.id.menu_citas == menuItem.getItemId( ) ) {
					Utilidades.printToastInfo( view.getContext( ), "Ya estas en citas" );
				} else {
					Utilidades.openActivityWithItemId( view.getActivity( ), menuItem.getItemId( ) );
				}
				
				return true;
			}
		};
	}
	
	@Override
	public void initListaCita( ) {
	
	}
	
	@Override
	public void initFormCita( ) {
		
		if ( this.formCita.getStatus( ) ) {
			Utilidades.setToolbar( this.view.getActivity( ), "Agregar cita" );
			this.formCita.getFragment( ).getBtnAction( ).setText( "Agregar" );
		} else {
			Utilidades.setToolbar( this.view.getActivity( ), "Editar cita" );
			this.formCita.getFragment( ).getBtnAction( ).setText( "Actualizar" );
		}
		
	}
	
	@Override
	public View.OnClickListener clickAgregarCita( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment( view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_citas_content, new RegistrarCitsaFragment( ) );
				( ( RegistrarCitsaFragment ) f ).setPresenter( ( ( CitasDashbordActivity ) view ).getPresenter( ) );
				( ( RegistrarCitsaFragment ) f ).setStatus( true );
				formCita = ( RegistrarCitsaFragment ) f;
			}
		};
	}
	
	@Override
	public View.OnClickListener clickActionCita( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				citaViewModel = ViewModelProviders.of( view.getActivity( ) ).get( CitaViewModel.class );
				
				if ( formCita.getFragment( ).getStatus( ) ) {
					citaViewModel.newCita( view.getContext( ), formCita.getFragment( ).getCitaEntity( ) );
					
				} else {
					citaViewModel.updateCita( view.getContext( ), formCita.getFragment( ).getCitaEntity( ) );
				}
				
			}
		};
	}
	
	@Override
	public View.OnClickListener clickCancelCita( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment( view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_citas_content, new ListaCitasFragment( ) );
				( ( ListaCitasFragment ) f )
						.setPresenter( ( ( CitasDashbordActivity ) view.getActivity( ) ).getPresenter( ) );
				listCita = ( ListaCitasFragment ) f;
			}
		};
	}
	
	@Override
	public View.OnClickListener clickDate( final RegistrarCitsaFragment fragment ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				final DatePickerFragment dialog = DatePickerFragment.newInstance(
						new DatePickerDialog.OnDateSetListener( ) {
							@Override
							public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {
								final String selectedDate = dayOfMonth + " / " + ( month + 1 ) + " / " + year;
								fragment.getEtDate( ).setText( selectedDate );
							}
						} );
				
				dialog.show( view.getActivity( ).getSupportFragmentManager( ), "datePicker" );
			}
		};
	}
	
}
