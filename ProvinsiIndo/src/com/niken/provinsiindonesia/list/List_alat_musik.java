package com.niken.provinsiindonesia.list;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.adapter.DaftarAdapter;
import com.niken.provinsiindonesia.entities.AlatMusik;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;

public class List_alat_musik extends ListActivity {
	private ProgressDialog pDialog;
	ArrayList daftarMusik;
	private String id_provinsi; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_alat_musik);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		daftarMusik = new ArrayList<AlatMusik>();
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		new MuatSemuaAlatMusik().execute();
	}

	 class MuatSemuaAlatMusik extends AsyncTask<String, String, String>{
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(List_alat_musik.this);
	            pDialog.setMessage("Memuat alat musik!");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }

			@Override
			protected String doInBackground(String... args) {
				try{											//url web service ini akan terpanggil ketika menu alat musik di klik
					JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/alatmusik");
					for(int i = 0; i < larik.length(); i++) {
						JSONObject baris = larik.getJSONObject(i);
						JSONObject prov = baris.getJSONObject("idProvinsi");
		                daftarMusik.add(
		                		new AlatMusik(
		                				prov.getString("nmProvinsi"),
		                				baris.getInt("idAlatmusik"),
		                				baris.getString("nmAlat"),
		                				baris.getString("gambarMusik"),
		                				baris.getString("ketAlat")
		                				)
		                		);
		            }
				}catch(Exception ex){
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
	                    setListAdapter(new DaftarAdapter(List_alat_musik.this, daftarMusik));
					}
				});
			}
	    }
}