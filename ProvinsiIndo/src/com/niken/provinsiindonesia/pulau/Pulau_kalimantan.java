package com.niken.provinsiindonesia.pulau;


import com.niken.provinsiindonesia.MuatProvinsi;
import com.niken.provinsiindonesia.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class Pulau_kalimantan extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pulau_kalimantan);
	}

public void Provinsi_kalimantan_barat (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kalimantan Barat");
	luncurkan.putExtra("id_provinsi", "4");
	startActivity(luncurkan);
}

public void Provinsi_kalimantan_selatan (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kalimantan Selatan");
	luncurkan.putExtra("id_provinsi", "5");
	startActivity(luncurkan);
}

public void Provinsi_kalimantan_tengah (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kalimantan Tengah");
	luncurkan.putExtra("id_provinsi", "6");
	startActivity(luncurkan);
}

public void Provinsi_kalimantan_timur (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kalimantan Timur");
	luncurkan.putExtra("id_provinsi", "7");
	startActivity(luncurkan);
}

public void Provinsi_kalimantan_utara (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kalimantan Utara");
	luncurkan.putExtra("id_provinsi", "34");
	startActivity(luncurkan);
}


}