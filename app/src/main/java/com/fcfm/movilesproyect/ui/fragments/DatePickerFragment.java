package com.fcfm.movilesproyect.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;


public class DatePickerFragment extends DialogFragment {
	
	private DatePickerDialog.OnDateSetListener listener;
	
	public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setListener(listener);
		return fragment;
	}
	
	public void setListener(DatePickerDialog.OnDateSetListener listener) {
		this.listener = listener;
	}
	
	@RequiresApi( api = Build.VERSION_CODES.N )
	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), listener, year, month, day);
	}
}
