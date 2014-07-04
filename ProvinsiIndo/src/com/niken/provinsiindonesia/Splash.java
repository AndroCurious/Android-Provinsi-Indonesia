package com.niken.provinsiindonesia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        new CountDownTimer(3000,1000) {
			@Override
			public void onFinish() {
				startActivity(new Intent(Splash.this, MainActivity.class));
				finish();
			}

		@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();
    }
}