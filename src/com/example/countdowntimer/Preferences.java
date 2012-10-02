package com.example.countdowntimer;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.preferences_screen);
		getPreferenceManager().setSharedPreferencesName("CountdownTimerPrefs");
		addPreferencesFromResource(R.xml.preferences);
		
	}

}
