package com.fcfm.movilesproyect.configurations;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.ui.activitys.CitasDashbordActivity;
import com.fcfm.movilesproyect.ui.activitys.DashbordActivity;
import com.fcfm.movilesproyect.ui.activitys.ProjectsDashboardActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import retrofit2.Response;

public class Utilidades {
	
	private static String TAG_LOG = "MY_APP";
	
	public final static int CODE_PERMISOS      = 1;
	public final static int CODE_CAMERA_PERFIL = 2;
	public final static int CODE_CAMERA_FONDO  = 3;
	public final static int CODE_GALERY_PERFIL = 4;
	public final static int CODE_GALERY_FONDO  = 5;
	
	public static void printToast( Context ctx, String txt ) {
		Toast.makeText( ctx, txt, Toast.LENGTH_LONG ).show( );
	}
	
	public static void printLog( String txt ) {
		Log.i( Utilidades.TAG_LOG, txt );
	}
	
	public static void failPeticionApi( Throwable t ) {
		Log.e( Utilidades.TAG_LOG, "Peticion FAIL" );
		Log.e( Utilidades.TAG_LOG, t.getMessage( ) + " : " + t.toString( ) );
	}
	
	public static void printResponseBody( Response response ) {
		Log.i( Utilidades.TAG_LOG, response.body( ).toString( ) );
	}
	
	public static void printResponseCode( Response response ) {
		Log.e( Utilidades.TAG_LOG, "Response code: " + response.code( ) );
		
		if ( response.code( ) != 200 ) Log.e( TAG_LOG, "Msg: " + response.message( ) );
	}
	
	public static SharedPreferences getSharedPreferencesInfoUser( Context ctx ) {
		return ctx.getSharedPreferences( "info_user", Context.MODE_PRIVATE );
	}
	
	public static void setRecuerdame( Context ctx, String txt ) {
		SharedPreferences prefs = ctx.getSharedPreferences( "recuerdame", Context.MODE_PRIVATE );
		SharedPreferences.Editor edit_prefs = prefs.edit( );
		
		edit_prefs.putString( "cuenta", txt );
		edit_prefs.apply( );
	}
	
	public static Fragment changeFragment( FragmentManager fg, int id, Fragment f ) {
		fg.beginTransaction( ).replace( id, f ).commit( );
		return f;
	}
	
	public static void setUserInfo( View view ) {
		ImageView perfil = view.findViewById( R.id.iv_header_navigation_img_user );
		ImageView fondo  = view.findViewById( R.id.iv_header_navigation_img_background );
		TextView  nombre = view.findViewById( R.id.tv_header_navigation_name_user );
		
		if ( User.getUser_active( ).getImg_perfil( ) != null )
			Picasso.get( ).load( User.getUser_active( ).getImg_perfil_uri( ) ).into( perfil );
		
		if ( User.getUser_active( ).getImg_fondo( ) != null )
			Picasso.get( ).load( User.getUser_active( ).getImg_fondo_uri( ) ).into( fondo );
		
		nombre.setText( User.getUser_active( ).getNombres( ) );
	}
	
	public static void setToolbar( AppCompatActivity view, String title ) {
		
		Toolbar toolbar = ( Toolbar ) view.findViewById( R.id.toolbar );
		toolbar.setTitle( title );
		view.setSupportActionBar( toolbar );
		view.getSupportActionBar( ).setHomeAsUpIndicator( R.drawable.ic_buerger_menu );
		view.getSupportActionBar( ).setDisplayHomeAsUpEnabled( true );
		
	}
	
	public static void checkedMenuItem( NavigationView navigationView, int posicion ) {
		navigationView.getMenu( ).getItem( posicion ).setChecked( true );
	}
	
	public static void openActivityWithItemId( AppCompatActivity view, int item_id ) {
		
		switch ( item_id ) {
			case R.id.menu_dashbord:
				view.startActivity( new Intent( view, DashbordActivity.class ) );
				view.finish( );
				break;
			case R.id.menu_proyectos:
				view.startActivity( new Intent( view, ProjectsDashboardActivity.class ) );
				view.finish( );
			case R.id.menu_citas:
				view.startActivity( new Intent( view, CitasDashbordActivity.class ) );
				view.finish( );
				break;
		}
		
	}
	
	public static void checkPermisos( AppCompatActivity activity ) {
		
		String[] permisos = new String[]{ Manifest.permission.CAMERA, Manifest.permission.INTERNET,
		                                  Manifest.permission.WRITE_EXTERNAL_STORAGE,
		                                  Manifest.permission.ACCESS_NETWORK_STATE,
		                                  Manifest.permission.ACCESS_FINE_LOCATION,
		                                  Manifest.permission.ACCESS_COARSE_LOCATION,
		                                  Manifest.permission.READ_EXTERNAL_STORAGE };
		
		for ( String permiso : permisos ) {
			
			if ( ContextCompat.checkSelfPermission( activity, permiso ) ==
			     PackageManager.PERMISSION_DENIED ) {
				ActivityCompat.requestPermissions( activity, permisos, Utilidades.CODE_PERMISOS );
			}
			
		}
		
	}
	
	public static void openCameraForResult( AppCompatActivity activity, int code ) {
		activity.startActivityForResult( new Intent( "android.media.action.IMAGE_CAPTURE" ), code );
	}
	
	public static void opneGaleryForResult( AppCompatActivity activity, int code ) {
		Intent intent = new Intent( Intent.ACTION_PICK );
		intent.setType( "image/*" );
		activity.startActivityForResult( intent, code );
	}
	
	public static File createFileBitmap( AppCompatActivity activity, Bitmap bitmap ) {
		try {
			OutputStream stream = new ByteArrayOutputStream( );
			
			if ( bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream ) ) {
				File file = new File( activity.getCacheDir( ),
				                      UUID.randomUUID( ).toString( ) + ".png" );
				
				if ( file.createNewFile( ) ) {
					FileOutputStream fos = new FileOutputStream( file );
					fos.write( ( ( ByteArrayOutputStream ) stream ).toByteArray( ) );
					fos.flush( );
					fos.close( );
					
					return file;
				}
			}
		}catch ( Exception e ) {
			e.printStackTrace( );
		}
		return null;
	}
	
	public static File createFileDrawable( AppCompatActivity activity, Drawable drawable ) {
		try {
			OutputStream stream = new ByteArrayOutputStream( );
			Bitmap       bitmap = ( ( BitmapDrawable ) drawable ).getBitmap( );
			
			if ( bitmap.getByteCount( ) >= 10485760 ) {
				Utilidades.printToast( activity.getApplicationContext( ),
				                       "La imagen es demaciado grande elija otra." );
				return null;
			}
			
			Utilidades.printLog( String.valueOf( bitmap.getByteCount( ) ) );
			
			if ( bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream ) ) {
				File file = new File( activity.getCacheDir( ),
				                      UUID.randomUUID( ).toString( ) + ".png" );
				
				if ( file.createNewFile( ) ) {
					FileOutputStream fos = new FileOutputStream( file );
					fos.write( ( ( ByteArrayOutputStream ) stream ).toByteArray( ) );
					fos.flush( );
					fos.close( );
					
					return file;
				}
			}
			
		}catch ( Exception e ) {
			e.printStackTrace( );
		}
		
		return null;
	}
}
