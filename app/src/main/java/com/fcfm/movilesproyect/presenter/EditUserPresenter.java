package com.fcfm.movilesproyect.presenter;

import android.view.View;

import com.fcfm.movilesproyect.apis.UserAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.interfaces.IEditUserMVP;
import com.fcfm.movilesproyect.models.User;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserPresenter implements IEditUserMVP.Presenter {
	
	private IEditUserMVP.View  view;
	private IEditUserMVP.Model model;
	
	public EditUserPresenter( IEditUserMVP.View view ) {
		this.view = view;
		
	}
	
	@Override
	public void init( ) {
		
		UserAPIService api = ApiUtils.getUserAPIService( );
		api.getUser( String.valueOf( User.getUser_active( ).getId( ) ) )
		   .enqueue( new Callback< User >( ) {
			   @Override
			   public void onResponse( Call< User > call, Response< User > response ) {
				
				   Utilidades.printResponseCode( response );
				
				   if ( response.isSuccessful( ) ) {
					   Utilidades.printResponseBody( response );
					
					   User.setUser_active( response.body( ) );
					   view.setData( User.getUser_active( ) );
				   }
				
			   }
			
			   @Override
			   public void onFailure( Call< User > call, Throwable t ) {
				   Utilidades.failPeticionApi( t );
			   }
		   } );
		
		this.view.setData( User.getUser_active( ) );
	}
	
	@Override
	public View.OnClickListener clickCamaraPerfil( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Utilidades
						.openCameraForResult( view.getActivity( ), Utilidades.CODE_CAMERA_PERFIL );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickCamaraFondo( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Utilidades.openCameraForResult( view.getActivity( ), Utilidades.CODE_CAMERA_FONDO );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickGaleryPerfil( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Utilidades
						.opneGaleryForResult( view.getActivity( ), Utilidades.CODE_GALERY_PERFIL );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickGaleryFondo( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Utilidades.opneGaleryForResult( view.getActivity( ), Utilidades.CODE_GALERY_FONDO );
			}
		};
	}
	
	private MultipartBody.Part getBody( File file ) {
		
		try {
			final RequestBody request = RequestBody
					                            .create( MediaType.parse( "multipart/form-data" ),
					                                     file );
			return MultipartBody.Part.createFormData( "file", file.getName( ), request );
		}catch ( NullPointerException e ) {
			e.printStackTrace( );
			Utilidades.printToast( view.getActivity( ), "Seleccione una imagen porfavor" );
			return null;
		}catch ( Exception e ) {
			e.printStackTrace( );
			Utilidades.printToast( view.getActivity( ), "Error vuelva a intentarlo" );
			return null;
		}
	}
	
	@Override
	public View.OnClickListener clickSavePerfil( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				UserAPIService api = ApiUtils.getUserAPIService( );
				api.saveImgPerfil( User.getUser_active( ).getId( ),
				                   getBody( view.getImgPerfil( ) ) )
				   .enqueue( new Callback< User >( ) {
					   @Override
					   public void onResponse( Call< User > call, Response< User > response ) {
						   Utilidades.printResponseCode( response );
						
						   if ( response.isSuccessful( ) ) {
							
							   Utilidades.printResponseBody( response );
							   Utilidades.printToast( view.getContext( ),
							                          "La imagen se a actualizado" );
						   }
					   }
					
					   @Override
					   public void onFailure( Call< User > call, Throwable t ) {
						   Utilidades.failPeticionApi( t );
					   }
				   } );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickSaveFondo( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				UserAPIService api = ApiUtils.getUserAPIService( );
				api.saveImgFondo( User.getUser_active( ).getId( ), getBody( view.getImgFondo( ) ) )
				   .enqueue( new Callback< User >( ) {
					   @Override
					   public void onResponse( Call< User > call, Response< User > response ) {
						   Utilidades.printResponseCode( response );
						
						   if ( response.isSuccessful( ) ) {
							
							   Utilidades.printResponseBody( response );
							   Utilidades.printToast( view.getContext( ),
							                          "La imagen se a actualizado" );
						   }
					   }
					
					   @Override
					   public void onFailure( Call< User > call, Throwable t ) {
						   Utilidades.failPeticionApi( t );
					   }
				   } );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickActualizar( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				User user = view.getData( );
				
				UserAPIService service = ApiUtils.getUserAPIService( );
				service.actualizarUser( user ).enqueue( new Callback< User >( ) {
					@Override
					public void onResponse( Call< User > call, Response< User > response ) {
						
						Utilidades.printResponseCode( response );
						
						if ( response.isSuccessful( ) ) {
							Utilidades.printResponseBody( response );
							
							User.setUser_active( response.body() );
							
							Utilidades.printToast( view.getContext( ),
							                       "Se a actualizado correctamente" );
							view.getActivity( ).finish( );
						}
					}
					
					@Override
					public void onFailure( Call< User > call, Throwable t ) {
						Utilidades.failPeticionApi( t );
					}
				} );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickCancelar( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				view.getActivity( ).finish( );
			}
		};
	}
}

