package com.niken.provinsiindonesia;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Tentangku extends Activity  {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tentangku);
	}
}
