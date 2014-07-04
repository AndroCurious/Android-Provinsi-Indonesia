package com.niken.provinsiindonesia.list;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.adapter.DaftarAdapter;
import com.niken.provinsiindonesia.entities.SeniTari;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;

public class List_tari_adat extends ListActivity {
	private ProgressDialog pDialog;
	ArrayList daftarTari;
	private String id_provinsi; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_tari_adat);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		 daftarTari = new ArrayList<SeniTari>();
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		new MuatSemuaTari().execute();
	}
	
	class MuatSemuaTari extends AsyncTask<String, String, String>{
		
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(List_tari_adat.this);
	            pDialog.setMessage("Memuat tari adat!");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }


		@Override
		protected String doInBackground(String... params) {
			try{
				JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/senitari");
				for(int i = 0; i < larik.length(); i++) {
					JSONObject baris =larik.getJSONObject(i);
					JSONObject prov = baris.getJSONObject("idProvinsi");
					daftarTari.add(
							new SeniTari(
									prov.getString("nmProvinsi"),
									baris.getInt("idTari"),
									baris.getString("nmTari"),
									baris.getString("gambarTari"),
									baris.getString("ketTari")));
				}
			}catch(Exception ex){}
			return null;
		}
		
		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					setListAdapter(
							new DaftarAdapter(List_tari_adat.this, 
		            				daftarTari)
							);
				}
			});
		}
		
	}

	
}