package com.example.countdowntimer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

public class TimerService extends Service {

	public Context mContext;
	public Integer counter;
	public Timer timer;
	public PowerManager.WakeLock wl;
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart(intent, startId);
		
		mContext = this;
		counter = intent.getIntExtra("counter", 0);
		if (counter != 0) {
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK + PowerManager.ON_AFTER_RELEASE, "My Tag");
			wl.acquire();
			startTimer();
		}
	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		timer.cancel();
		if(wl.isHeld()) {
			wl.release();
		}
		super.onDestroy();
		
	}

	public void startTimer() {
		// TODO 自動生成されたメソッド・スタブ
		if(timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		final android.os.Handler handler = new android.os.Handler();
		
		timer.schedule(
				new TimerTask() {

					@Override
					public void run() {
						// TODO 自動生成されたメソッド・スタブ
						handler.post(new Runnable() {
							public void run () {
								if (counter == -1) {
									timer.cancel();
									if (wl.isHeld()) {
										wl.release();
									}
									showAlarm();
								} else {
									CountdownTimerActivity.countdown(counter);
									counter = counter - 1;
								}	
							}

						});
					}
					
				}, 0, 1000);
	}


	private void showAlarm() {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = new Intent(mContext, TimerService.class);
		mContext.stopService(intent);
		intent = new Intent(mContext, AlarmDialog.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
