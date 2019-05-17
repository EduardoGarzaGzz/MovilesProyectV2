package com.fcfm.movilesproyect.presenter;

import com.fcfm.movilesproyect.presenter.interfaces.ITaskDashboardMVP;

public class TaskDashboardPresenter implements ITaskDashboardMVP.Presenter {
	
	private ITaskDashboardMVP.View view;
	
	public TaskDashboardPresenter( ITaskDashboardMVP.View view ) {
		this.view = view;
	}
}
