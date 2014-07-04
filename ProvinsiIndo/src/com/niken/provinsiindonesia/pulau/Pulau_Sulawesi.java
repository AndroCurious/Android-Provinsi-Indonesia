package com.niken.provinsiindonesia.pulau;


import com.niken.provinsiindonesia.MuatProvinsi;
import com.niken.provinsiindonesia.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class Pulau_Sulawesi extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pulau_sulawesi);
	}

public void Provinsi_gorontalo (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Gorontalo");
	luncurkan.putExtra("id_provinsi", "25");
	startActivity(luncurkan);
}

public void Provinsi_sulawesi_barat (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sulawesi Barat");
	luncurkan.putExtra("id_provinsi", "26");
	startActivity(luncurkan);
}

public void Provinsi_sulawesi_selatan (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sulawesi Selatan");
	luncurkan.putExtra("id_provinsi", "27");
	startActivity(luncurkan);
}

public void Provinsi_sulawesi_tengah (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sulawesi Tengah");
	luncurkan.putExtra("id_provinsi", "28");
	startActivity(luncurkan);
}

public void Provinsi_sulawesi_tenggara (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sulawesi Tenggara");
	luncurkan.putExtra("id_provinsi", "29");
	startActivity(luncurkan);
}

public void Provinsi_sulawesi_utara (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sulawesi Utara");
	luncurkan.putExtra("id_provinsi", "30");
	startActivity(luncurkan);
}
}