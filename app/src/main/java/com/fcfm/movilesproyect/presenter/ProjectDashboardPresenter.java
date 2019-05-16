package com.fcfm.movilesproyect.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.viewmodel.ProjectViewModel;
import com.fcfm.movilesproyect.ui.adapters.ListProjectsAdapter;
import com.fcfm.movilesproyect.db.apis.ProjectAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IProjectDashboard;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.ui.fragments.ListaProjectsFragment;
import com.fcfm.movilesproyect.ui.fragments.RegistrarProjectFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDashboardPresenter implements IProjectDashboard.Presenter {
	
	private ProjectViewModel model;
	
	private IProjectDashboard.View      view;
	private IProjectDashboard.View.List view_list;
	private IProjectDashboard.View.Form view_form;
	
	public ProjectDashboardPresenter( IProjectDashboard.View view ) {
		this.view = view;
	}
	
	@Override
	public void init( ) {
		
//		Utilidades.printLog( "Metodo init inicinado prueba" );
//
//		this.model = ViewModelProviders.of( this.view.getActivity( ) )
//		                               .get( ProjectViewModel.class );
//		Utilidades.printLog( "Model se a instanciado" );
//
//		this.model.getProjects( )
//		          .observe( this.view.getActivity( ), new Observer< List< Project > >( ) {
//			          @Override
//			          public void onChanged( @Nullable List< Project > projects ) {
//				          Utilidades.printLog( "In observable: " + projects.size( ) );
//			          }
//		          } );
//		Utilidades.printLog( "Model observable escuchando" );
//
//		Project[] projects = new Project[8];
//		projects[0] = new Project( 1, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[1] = new Project( 2, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[2] = new Project( 3, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[3] = new Project( 4, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[4] = new Project( 5, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[5] = new Project( 6, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[6] = new Project( 7, "Prueba", "prueba", new Date( ), new Date( ) );
//		projects[7] = new Project( 8, "Prueba", "prueba", new Date( ), new Date( ) );
//
//		this.model.insertarProject( projects );
//		Utilidades.printLog( "Insercion correcta" );
		
	}
	
	@Override
	public void setViewList( IProjectDashboard.View.List view_list ) {
		this.view_list = view_list;
	}
	
	@Override
	public void setViewForm( IProjectDashboard.View.Form view_form ) {
		this.view_form = view_form;
	}
	
	@Override
	public void initViewList( final RecyclerView recycler_view, final RecyclerView.Adapter adapter,
	                          RecyclerView.LayoutManager layout_manager ) {
		
		Utilidades.setToolbar( this.view.getActivity( ),
		                       "Proyectos de " + User.getUser_active( ).getNombres( ) );
		
		recycler_view.setHasFixedSize( true );
		recycler_view.setItemAnimator( new DefaultItemAnimator( ) );
		recycler_view.setLayoutManager( layout_manager );
		recycler_view.setAdapter( adapter );
		
		
		this.view_list.getInfoText( ).setText( "Buscando proyectos" );
		
		ProjectAPIService service = ApiUtils.getProjectService( );
		service.getProjects( User.getUser_active( ).getId( ) )
		       .enqueue( new Callback< Project[] >( ) {
			       @Override
			       public void onResponse( Call< Project[] > call,
			                               Response< Project[] > response ) {
				
				       Utilidades.printResponseCode( response );
				
				       if ( response.isSuccessful( ) ) {
					       Utilidades.printResponseBody( response );
					
					       if ( response.body( ).length > 0 ) {
						       view_list.getLista( ).setVisibility( View.VISIBLE );
						       view_list.getInfoText( ).setVisibility( View.INVISIBLE );
						       view_list.setListProjects( Arrays.asList( response.body( ) ) );
					       }
					       else view_list.getInfoText( ).setText( "No hay proyectos" );
					
				       }
				
			       }
			
			       @Override
			       public void onFailure( Call< Project[] > call, Throwable t ) {
				       Utilidades.failPeticionApi( t );
				       view_list.getInfoText( )
				                .setText( "Error no logramos conectarnos con el servidor" );
			       }
		       } );
	}
	
	@Override
	public void initViewForm( ) {
		Utilidades.setToolbar( this.view.getActivity( ), "Agregar un nuevo proyecto" );
	}
	
	@Override
	public ListProjectsAdapter.OnItemClickListener clickItemListProject( ) {
		return new ListProjectsAdapter.OnItemClickListener( ) {
			@Override
			public void onItemClick( Project project, int position ) {
				Utilidades.printToast( view.getContext( ), "Click: " + project.getTitle( ) );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickAgregarProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_project_content, new RegistrarProjectFragment( ) );
				( ( RegistrarProjectFragment ) f ).setPresenter( view.getPresenter( ) );
				( ( RegistrarProjectFragment ) f ).setStatus( true );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickActionProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				
				ProjectAPIService service = ApiUtils.getProjectService( );
				
				if ( view_form.getStatus( ) ) { // Register project
					Utilidades.printLog( "Registrando proyecto" );
					service.registerProject( User.getUser_active( ).getId( ),
					                         view_form.getProject( ) )
					       .enqueue( new Callback< Project >( ) {
						       @Override
						       public void onResponse( Call< Project > call,
						                               Response< Project > response ) {
							       Utilidades.printResponseCode( response );
							
							       if ( response.isSuccessful( ) ) {
								       Utilidades.printResponseBody( response );
								       Utilidades.printToast( view.getContext( ),
								                              "El proyecto se guardo" );
								
								       Fragment f = Utilidades.changeFragment(
										       view.getActivity( ).getSupportFragmentManager( ),
										       R.id.frag_dashbord_project_content,
										       new ListaProjectsFragment( ) );
								       ( ( ListaProjectsFragment ) f )
										       .setPresenter( view.getPresenter( ) );
							       }
						       }
						
						       @Override
						       public void onFailure( Call< Project > call, Throwable t ) {
							       Utilidades.failPeticionApi( t );
						       }
					       } );
					
				}
				else { // Update project
					Utilidades.printLog( "Actualizando proyecto" );
					// TODO implementar update
				}
			}
		};
	}
	
	@Override
	public View.OnClickListener clickCacelarProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_project_content, new ListaProjectsFragment( ) );
				( ( ListaProjectsFragment ) f ).setPresenter( view.getPresenter( ) );
			}
		};
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
				if ( R.id.menu_proyectos == menuItem.getItemId( ) )
					Utilidades.printToast( view.getContext( ), "Ya estas en proyectos" );
				else
					Utilidades.openActivityWithItemId( view.getActivity( ), menuItem.getItemId( ) );
				
				return true;
			}
		};
	}
}
