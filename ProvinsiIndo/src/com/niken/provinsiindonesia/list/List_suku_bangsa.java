package com.niken.provinsiindonesia.list;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.adapter.DaftarAdapter;
import com.niken.provinsiindonesia.entities.Suku;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;

public class List_suku_bangsa extends ListActivity {
	private ProgressDialog pDialog;
	ArrayList daftarSuku;
	 private String id_provinsi; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_suku_bangsa);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		daftarSuku = new ArrayList< Suku>();
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		new MuatSemuaSuku().execute();
	}

	class MuatSemuaSuku extends AsyncTask<String, String, String>{
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(List_suku_bangsa.this);
	            pDialog.setMessage("Memuat suku bangsa!");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... params) {
			try{
				JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/suku");
				for(int i = 0; i < larik.length(); i++) {
					JSONObject baris =larik.getJSONObject(i);
					JSONObject prov = baris.getJSONObject("idProvinsi");
	                daftarSuku.add(new Suku(prov.getString("nmProvinsi"),baris.getInt("idSuku"), baris.getString("nmSuku"), baris.getString("gambarSuku"), baris.getString("ket")));
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
							new DaftarAdapter(List_suku_bangsa.this,
		            				daftarSuku)
							);
				}
			});
		}
		
	}
	
}