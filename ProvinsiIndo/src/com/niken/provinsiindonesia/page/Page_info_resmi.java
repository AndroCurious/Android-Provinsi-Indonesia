package com.niken.provinsiindonesia.page;

import java.io.InputStream;
import java.net.URL;
import org.json.JSONObject;
import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.entities.InfoResmi;
import com.niken.provinsiindonesia.tools.JSONParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Page_info_resmi extends Activity {
	private ProgressDialog pDialog;
	
	private String id_provinsi; 
	private TextView teks;
	private LinearLayout ll ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_info_resmi);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		teks = (TextView)findViewById(R.id.infone);
		ll=(LinearLayout)findViewById(R.id.idprovinsi);

		new MuatHalamanInfo().execute();
	}
	private class LoadImageTask extends AsyncTask<Object, Void, Bitmap> {
		 private LinearLayout imv;
	     private String url;
	     public LoadImageTask(LinearLayout imv, String url){
	    	 this.imv = imv;
	    	 this.url = url;
	    	 }
	    @Override
		protected Bitmap doInBackground(Object... params){
	    	Bitmap bitmap = null;
			try {
				bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
			} catch (Exception e) {
				Log.d("gantine","santai");
				e.printStackTrace();
			} 
			return bitmap;
		}
		@Override
		protected void onPostExecute(final Bitmap result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					BitmapDrawable background = new BitmapDrawable(getResources(),result);
					imv.setBackgroundDrawable(background);
				}
			});
				
	    }	
	}
	class MuatHalamanInfo extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Page_info_resmi.this);
            pDialog.setMessage("Memuat info resmi...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... args) {
			try{
				JSONObject baris  = JSONParser.getSingleResult("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/info");
				info = new InfoResmi(
	                				baris.getInt("idInfo"),
	                				baris.getString("gambarPeta"),
	                				baris.getString("keteranganinfo")
	                				);
				Log.d("xxxKetInfo",info.getKeteranganinfo());
				Log.d("xxxGambar",info.getGambarPeta());
	             new LoadImageTask(ll, "http://192.168.173.1:8080/resources/"+info.getGambarPeta()).execute();
			}catch(Exception ex){
			}
			return null;
		}
		private InfoResmi info;
		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Log.d("ksgkah",info.getKeteranganinfo());
                    teks.setText(  Html.fromHtml(info.getKeteranganinfo()));
				}
			});
		}
    }
}
