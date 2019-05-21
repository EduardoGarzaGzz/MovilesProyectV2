package com.fcfm.movilesproyect.ui.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.models.CitaEntity;
import com.fcfm.movilesproyect.presenter.interfaces.ICitaDashboardMVP;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarCitsaFragment extends Fragment implements OnMapReadyCallback, ICitaDashboardMVP.View.FormCita {
	
	private ICitaDashboardMVP.Presenter presenter;
	private boolean status;
	private CitaEntity citaEntity;
	
	private MapView mv;
	private LocationManager locManager;
	private Location loc;
	private LatLng latLng;
	
	private EditText etTitle;
	private EditText etDescription;
	private EditText etDate;
	private TextView tvLog;
	private TextView tvLat;
	private Button btnAction;
	private Button btnCancel;
	
	
	public RegistrarCitsaFragment( ) {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View view = inflater.inflate( R.layout.fragment_registrar_citsa, container, false );
		
		this.etTitle = view.findViewById( R.id.et_frag_form_citas_title );
		this.etDescription = view.findViewById( R.id.et_frag_form_citas_description );
		this.etDate = view.findViewById( R.id.date_frag_citas_date );
		this.tvLog = view.findViewById( R.id.tv_frag_form_citas_log );
		this.tvLat = view.findViewById( R.id.tv_frag_form_citas_lat );
		this.btnAction = view.findViewById( R.id.btn_frag_form_citas_action );
		this.btnCancel = view.findViewById( R.id.btn_frag_form_citas_cancel );
		
		
		this.mv = view.findViewById( R.id.mv_registrar_citas_maps );
		this.mv.getMapAsync( this );
		this.mv.onCreate( savedInstanceState );
		this.locManager = ( LocationManager ) getActivity( )
				.getSystemService( Context.LOCATION_SERVICE );
		
		if ( ActivityCompat.checkSelfPermission( getContext( ),
				Manifest.permission.ACCESS_FINE_LOCATION ) ==
				PackageManager.PERMISSION_GRANTED ) {
			this.loc = locManager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
		} else {
			ActivityCompat.requestPermissions( getActivity( ), new String[]{
					Manifest.permission.ACCESS_FINE_LOCATION }, 200 );
		}
		
		this.presenter.initFormCita( );
		
		return view;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.mv.onStart( );
		
	}
	
	@Override
	public void onMapReady( GoogleMap googleMap ) {
		
		if ( this.loc == null ) {
			Toast.makeText( getContext( ), "No tienes permiso de ubicacion", Toast.LENGTH_LONG )
					.show( );
			return;
		}
		
		final GoogleMap mapa = googleMap;
		LatLng coordinate = new LatLng( this.loc.getLatitude( ), this.loc.getLongitude( ) );
		
		setTvLatText( String.valueOf( this.loc.getLatitude( ) ) );
		setTvLogText( String.valueOf( this.loc.getLongitude( ) ) );
		
		mapa.addMarker( new MarkerOptions( ).position( coordinate ).title( "YO" ) );
		CameraUpdate location = CameraUpdateFactory.newLatLngZoom( coordinate, 1.0f );
		mapa.animateCamera( location );
		mapa.moveCamera( CameraUpdateFactory.newLatLng( coordinate ) );
		mapa.setMyLocationEnabled( true );
		
		mapa.setOnMapClickListener( new GoogleMap.OnMapClickListener( ) {
			@Override
			public void onMapClick( LatLng lng ) {
				mapa.clear( );
				mapa.addMarker( new MarkerOptions( ).position( lng ).title( "YO" ) );
				CameraUpdate location = CameraUpdateFactory.newLatLngZoom( lng, 1.0f );
				mapa.animateCamera( location );
				mapa.moveCamera( CameraUpdateFactory.newLatLng( lng ) );
				
				latLng = lng;
				
				setTvLatText( String.valueOf( lng.latitude ) );
				setTvLogText( String.valueOf( lng.longitude ) );
				
			}
		} );
	}
	
	@Override
	public void onDestroy( ) {
		super.onDestroy( );
		
		this.mv.onDestroy( );
	}
	
	@Override
	public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions,
	                                        @NonNull int[] grantResults ) {
		super.onRequestPermissionsResult( requestCode, permissions, grantResults );
		
		switch ( requestCode ) {
			case 200:
				if ( grantResults.length > 0 &&
						grantResults[ 0 ] == PackageManager.PERMISSION_GRANTED ) {
					Toast.makeText( getContext( ), "Gracias", Toast.LENGTH_LONG ).show( );
				} else {
					Toast.makeText( getContext( ),
							"Maps puede no funcionar correctamente hasta tener el permiso",
							Toast.LENGTH_LONG ).show( );
				}
				break;
			default:
				Toast.makeText( getContext( ),
						"Maps puede no funcionar correctamente hasta tener el permiso",
						Toast.LENGTH_LONG ).show( );
		}
		
	}
	
	@Override
	public RegistrarCitsaFragment getFragment( ) {
		return this;
	}
	
	@Override
	public void setPresenter( ICitaDashboardMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
	
	@Override
	public void setStatus( boolean status ) {
		this.status = status;
	}
	
	@Override
	public boolean getStatus( ) {
		return this.status;
	}
	
	public void setTvLogText( String txt ) {
		this.tvLog.setText( "Log: " + txt );
	}
	
	public void setTvLatText( String txt ) {
		this.tvLat.setText( "Lat: " + txt );
	}
	
	public EditText getEtTitle( ) {
		return etTitle;
	}
	
	public EditText getEtDescription( ) {
		return etDescription;
	}
	
	public TextView getTvLog( ) {
		return tvLog;
	}
	
	public TextView getTvLat( ) {
		return tvLat;
	}
	
	public EditText getEtDate( ) {
		return etDate;
	}
	
	public void setEtDate( EditText etDate ) {
		this.etDate = etDate;
	}
	
	public Button getBtnAction( ) {
		return btnAction;
	}
	
	public Button getBtnCancel( ) {
		return btnCancel;
	}
	
	public CitaEntity getCitaEntity( ) {
		if ( this.citaEntity == null ) this.citaEntity = new CitaEntity( );
		
		this.citaEntity.title = this.etTitle.getText( ).toString( ).trim( );
		this.citaEntity.description = this.etDescription.getText( ).toString( ).trim( );
		this.citaEntity.lat = ( float ) this.latLng.latitude;
		this.citaEntity.log = ( float ) this.latLng.longitude;
		
		try {
			if ( this.etDate.getText( ).toString( ).equals( "" ) ) {
				this.citaEntity.date = null;
			} else {
				this.citaEntity.date = new Date( String.valueOf( new SimpleDateFormat( "dd/MM/yyyy" ).parse( this.etDate.getText().toString( ) ) ) );
			}
		} catch ( ParseException e ) {
			e.printStackTrace( );
		}
		
		return citaEntity;
	}
	
	public void setCitaEntity( CitaEntity citaEntity ) {
		this.citaEntity = citaEntity;
	}
	
	public LatLng getLatLng( ) {
		return latLng;
	}
	
	public void setLatLng( LatLng latLng ) {
		this.latLng = latLng;
	}
}
