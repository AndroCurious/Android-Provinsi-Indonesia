package com.niken.provinsiindonesia.pulau;


import com.niken.provinsiindonesia.MuatProvinsi;
import com.niken.provinsiindonesia.R; 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class Kep_nusa_tenggara extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.kep_nusa_tenggara);
	}

public void Provinsi_bali (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Bali");
	luncurkan.putExtra("id_provinsi", "31");
	startActivity(luncurkan);
}

public void Provinsi_ntb (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Nusa Tenggara Barat");
	luncurkan.putExtra("id_provinsi", "32");
	startActivity(luncurkan);
}

public void Provinsi_ntt (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Nusa Tenggara Timur");
	luncurkan.putExtra("id_provinsi", "33");
	startActivity(luncurkan);
}
}