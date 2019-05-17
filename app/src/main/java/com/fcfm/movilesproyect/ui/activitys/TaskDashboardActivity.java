package com.fcfm.movilesproyect.ui.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.presenter.interfaces.ITaskDashboardMVP;

public class TaskDashboardActivity extends AppCompatActivity implements ITaskDashboardMVP.View {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_task_dashboard );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
	}
}
