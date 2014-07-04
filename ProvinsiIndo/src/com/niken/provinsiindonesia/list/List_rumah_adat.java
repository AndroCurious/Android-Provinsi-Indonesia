package com.niken.provinsiindonesia.list;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.adapter.DaftarAdapter;
import com.niken.provinsiindonesia.entities.RumahAdat;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;

public class List_rumah_adat extends ListActivity {
	private ProgressDialog pDialog;
	ArrayList daftarRumah;
	 private String id_provinsi; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_rumah_adat);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		daftarRumah = new ArrayList<RumahAdat>();
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		new MuatRumahAdat().execute();
	}
	class MuatRumahAdat extends AsyncTask<String, String, String>{
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(List_rumah_adat.this);
	            pDialog.setMessage("Memuat rumah adat!");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... params) {
			try{
				JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/rumahadat");
				for(int i = 0; i < larik.length(); i++) {
					JSONObject baris =larik.getJSONObject(i);
					JSONObject prov = baris.getJSONObject("idProvinsi");
					daftarRumah.add(new RumahAdat(
							prov.getString("nmProvinsi"),
							baris.getInt("idRumah"),
							baris.getString("nmRumah"), 
							baris.getString("gambarRmh"), 
							baris.getString("ketRumah")));
				}
			}catch(Exception ex){
				
			}
			return null;
		}
		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					setListAdapter(
					new DaftarAdapter(List_rumah_adat.this, daftarRumah));
				}
			});
		}
	}
	

	
}