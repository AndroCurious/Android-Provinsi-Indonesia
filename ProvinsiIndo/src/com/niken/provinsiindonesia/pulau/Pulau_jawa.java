package com.niken.provinsiindonesia.pulau;


import com.niken.provinsiindonesia.MuatProvinsi;
import com.niken.provinsiindonesia.R; 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class Pulau_jawa extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pulau_jawa);
	}

public void Provinsi_banten (View view) {//fungsi ini diacu oleh file pulau_jawa.xml, dan harus ada View
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Banten");
	luncurkan.putExtra("id_provinsi", "8");
	startActivity(luncurkan);
}

public void Provinsi_diy (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "DIY");
	luncurkan.putExtra("id_provinsi", "9");
	startActivity(luncurkan);
}

public void Provinsi_dki (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "DKI Jakarta");
	luncurkan.putExtra("id_provinsi", "10");
	startActivity(luncurkan);
}

public void Provinsi_jawa_barat (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Jawa Barat");
	luncurkan.putExtra("id_provinsi", "1");
	startActivity(luncurkan);
}

public void Provinsi_jawa_tengah (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Jawa Tengah");
	luncurkan.putExtra("id_provinsi", "2");
	startActivity(luncurkan);
}

public void Provinsi_jawa_timur (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Jawa Timur");
	luncurkan.putExtra("id_provinsi", "3");
	startActivity(luncurkan);
}
}