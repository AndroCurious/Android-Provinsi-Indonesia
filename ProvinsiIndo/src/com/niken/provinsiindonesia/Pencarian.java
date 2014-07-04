package com.niken.provinsiindonesia;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.adapter.DaftarAdapter;
import com.niken.provinsiindonesia.entities.AlatMusik;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Pencarian extends ListActivity implements TextWatcher {
	private ProgressDialog pDialog;
	ArrayList daftarMusik;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pencarian);
		daftarMusik = new ArrayList<AlatMusik>();
		EditText kotakCari = (EditText) findViewById(R.id.kotakCari);
		kotakCari.addTextChangedListener(this);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		new MuatSemuaAlatMusik().execute(s.toString());
	}

	class MuatSemuaAlatMusik extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Pencarian.this);
			pDialog.setMessage("Memuat pencarian!");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			try {
				JSONArray larik = JSONParser
						.getResultList("http://192.168.173.1/provniken/cari.php?namaTabel=alat_musik&kataKunci="
								+ args[0]);
				for (int i = 0; i < larik.length(); i++) {
					JSONObject baris = larik.getJSONObject(i);
					JSONObject prov = baris.getJSONObject("idProvinsi");
					daftarMusik.add(new AlatMusik(prov.getString("nmProvinsi"),
							baris.getInt("idAlatmusik"), baris
									.getString("nmAlat"), baris
									.getString("gambarMusik"), baris
									.getString("ketAlat")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					setListAdapter(new DaftarAdapter(Pencarian.this,
							daftarMusik));
				}
			});
		}
	}
}
