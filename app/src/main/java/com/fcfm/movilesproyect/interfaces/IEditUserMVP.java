package com.fcfm.movilesproyect.interfaces;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.fcfm.movilesproyect.models.User;

import java.io.File;

public interface IEditUserMVP {
	
	interface View {
		
		void setData( User user );
		
		Context getContext( );
		
		AppCompatActivity getActivity( );
		
		File getImgPerfil( );
		
		File getImgFondo( );
	}
	
	interface Presenter {
		
		void init();
		
		android.view.View.OnClickListener clickCamaraPerfil( );
		
		android.view.View.OnClickListener clickCamaraFondo( );
		
		android.view.View.OnClickListener clickGaleryPerfil( );
		
		android.view.View.OnClickListener clickGaleryFondo( );
		
		android.view.View.OnClickListener clickSavePerfil( );
		
		android.view.View.OnClickListener clickSaveFondo( );
		
		android.view.View.OnClickListener clickActualizar( );
		
		android.view.View.OnClickListener clickCancelar( );
	}
	
	interface Model {
	
	}
}
