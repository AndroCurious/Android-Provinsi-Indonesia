package com.niken.provinsiindonesia.pulau;


import com.niken.provinsiindonesia.MuatProvinsi;
import com.niken.provinsiindonesia.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class Pulau_Sumatera extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pulau_sumatera);
	}

public void Provinsi_bengkulu (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Bengkulu");
	luncurkan.putExtra("id_provinsi", "11");
	startActivity(luncurkan);
}

public void Provinsi_jambi (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Jambi");
	luncurkan.putExtra("id_provinsi", "12");
	startActivity(luncurkan);
}

public void Provinsi_kep_bangka_belitung (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kepulauan Bangka Belitung");
	luncurkan.putExtra("id_provinsi", "13");
	startActivity(luncurkan);
}

public void Provinsi_kep_riau (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Kepulauan Riau");
	luncurkan.putExtra("id_provinsi", "14");
	startActivity(luncurkan);
}

public void Provinsi_lampung (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Lampung");
	luncurkan.putExtra("id_provinsi", "15");
	startActivity(luncurkan);
}

public void Provinsi_nad (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Aceh");
	luncurkan.putExtra("id_provinsi", "16");
	startActivity(luncurkan);
}

public void Provinsi_riau (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Riau");
	luncurkan.putExtra("id_provinsi", "17");
	startActivity(luncurkan);
}

public void Provinsi_Sumatera_barat (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sumatera Barat");
	luncurkan.putExtra("id_provinsi", "18");
	startActivity(luncurkan);
}

public void Provinsi_Sumatera_Selatan (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sumatera Selatan");
	luncurkan.putExtra("id_provinsi", "19");
	startActivity(luncurkan);
}

public void Provinsi_Sumatera_utara (View view) {
	Intent luncurkan = new Intent(getApplicationContext(), MuatProvinsi.class);
	luncurkan.putExtra("namaprovinsi", "Sumatera Utara");
	luncurkan.putExtra("id_provinsi", "20");
	startActivity(luncurkan);
}
}