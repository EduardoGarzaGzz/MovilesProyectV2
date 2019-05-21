package com.fcfm.movilesproyect.db.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.models.CitaEntity;
import com.fcfm.movilesproyect.db.repository.CitaRespository;

import java.util.List;

public class CitaViewModel extends AndroidViewModel {
	
	CitaRespository citaRespository;
	
	public CitaViewModel( @NonNull Application application ) {
		super( application );
		
		this.citaRespository = new CitaRespository( application );
	}
	
	public LiveData< List< CitaEntity > > getCitas( ) {
		return this.citaRespository.getAll( );
	}
	
	public void newCita( Context ctx, CitaEntity citaEntity ) {
		
		if ( citaEntity.title == null || citaEntity.title.equals( "" ) || citaEntity.title.length( ) < 2 ) {
			Utilidades.printToastError( ctx, "El titulo no puede estar vacio" );
		}
		
		this.citaRespository.addCita( ctx, citaEntity );
	}
	
	public void updateCita( Context ctx, CitaEntity citaEntity ) {
		
		if ( citaEntity.title == null || citaEntity.title.equals( "" ) || citaEntity.title.length( ) < 2 ) {
			Utilidades.printToastError( ctx, "El titulo no puede estar vacio" );
		}
		
		this.citaRespository.updateCita( ctx, citaEntity );
	}
}
