package com.example.countdowntimer;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.preferences_screen);
		getPreferenceManager().setSharedPreferencesName("CountdownTimerPrefs");
		addPreferencesFromResource(R.xml.preferences);
		
	}

}
