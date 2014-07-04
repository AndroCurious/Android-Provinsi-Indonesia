package com.niken.provinsiindonesia.list;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.adapter.DaftarAdapter;
import com.niken.provinsiindonesia.entities.AlatMusik;
import com.niken.provinsiindonesia.entities.LaguDaerah;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
public class List_lagu_daerah extends ListActivity {
	private ProgressDialog pDialog;
	ArrayList daftarLagu;
	private String id_provinsi; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_lagu_daerah);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		daftarLagu = new ArrayList<AlatMusik>();
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		new MuatSemuaLaguDaerah().execute();
	}
	class MuatSemuaLaguDaerah extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(List_lagu_daerah.this);
            pDialog.setMessage("Memuat lagu daerah!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... args) {
			try{
				JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/lagudaerah");
				for(int i = 0; i < larik.length(); i++) {
					JSONObject baris = larik.getJSONObject(i);
					JSONObject prov = baris.getJSONObject("idProvinsi");
					daftarLagu.add(
	                		new LaguDaerah(
	                				prov.getString("nmProvinsi"),
	                				baris.getInt("idLaguDaerah"),
	                				baris.getString("nmLaguDaerah"),
	                				baris.getString("laguDaerah"),
	                				baris.getString("linkLagudaerah")
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
                    setListAdapter(new DaftarAdapter(List_lagu_daerah.this, daftarLagu));
				}
			});
		}
    }
}