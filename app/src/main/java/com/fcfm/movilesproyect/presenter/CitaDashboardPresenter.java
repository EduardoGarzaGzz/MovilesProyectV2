package com.fcfm.movilesproyect.presenter;

import android.support.v4.app.Fragment;
import android.view.View;
import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.ICitaDashboardMVP;
import com.fcfm.movilesproyect.ui.activitys.CitasDashbordActivity;
import com.fcfm.movilesproyect.ui.fragments.ListaCitasFragment;
import com.fcfm.movilesproyect.ui.fragments.RegistrarCitsaFragment;

public class CitaDashboardPresenter implements ICitaDashboardMVP.Presenter {
	
	private ICitaDashboardMVP.View view;
	private ICitaDashboardMVP.View.ListCita listCita;
	private ICitaDashboardMVP.View.FormCita formCita;
	
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
				
				if ( formCita.getFragment( ).getStatus( ) ) {
					// TODO registrar cita
				} else {
					// TODO actualizar cita
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
	
}
