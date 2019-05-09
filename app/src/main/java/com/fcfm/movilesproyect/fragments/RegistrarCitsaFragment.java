package com.fcfm.movilesproyect.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarCitsaFragment extends Fragment implements OnMapReadyCallback {
	
	private MapView         mv;
	private LocationManager locManager;
	private Location        loc;
	
	
	public RegistrarCitsaFragment( ) {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View v = inflater.inflate( R.layout.fragment_registrar_citsa, container, false );
		
		this.mv = v.findViewById( R.id.mv_registrar_citas_maps );
		this.mv.getMapAsync( this );
		this.mv.onCreate( savedInstanceState );
		this.locManager = ( LocationManager ) getActivity( )
				                                      .getSystemService( Context.LOCATION_SERVICE );
		
		if ( ActivityCompat.checkSelfPermission( getContext( ),
		                                         Manifest.permission.ACCESS_FINE_LOCATION ) ==
		     PackageManager.PERMISSION_GRANTED ) {
			this.loc = locManager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
		}
		else {
			ActivityCompat.requestPermissions( getActivity( ), new String[]{
					Manifest.permission.ACCESS_FINE_LOCATION }, 200 );
		}
		
		return v;
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
		
		GoogleMap mapa       = googleMap;
		LatLng    coordinate = new LatLng( this.loc.getLatitude( ), this.loc.getLongitude( ) );
		
		mapa.addMarker( new MarkerOptions( ).position( coordinate ).title( "YO" ) );
		CameraUpdate location = CameraUpdateFactory.newLatLngZoom( coordinate, 15.0f );
		mapa.animateCamera( location );
		mapa.moveCamera( CameraUpdateFactory.newLatLng( coordinate ) );
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
				}
				else {
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
}
