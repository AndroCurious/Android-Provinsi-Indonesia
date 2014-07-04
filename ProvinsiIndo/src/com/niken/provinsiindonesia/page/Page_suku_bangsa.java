package com.niken.provinsiindonesia.page;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.niken.provinsiindonesia.HorizontalListView;
import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.adapter.tagAdapter;
import com.niken.provinsiindonesia.entities.Provinsi;
import com.niken.provinsiindonesia.entities.Suku;
import com.niken.provinsiindonesia.tools.JSONParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Page_suku_bangsa extends Activity {
	ArrayList<Provinsi> daftarProvinsi;
	Suku suku;
	HorizontalListView taggingeBro ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_suku_bangsa);
		suku  = (Suku)getIntent().getExtras().get("item");
		setTitle("Provinsi "+suku.getNmProvinsi());
		taggingeBro = (HorizontalListView) findViewById(R.id.daftarTag);
		daftarProvinsi = new ArrayList<Provinsi>();
		new MuatSemuaTag().execute();
		TextView namasuku = (TextView)findViewById(R.id.namaSuku);
		namasuku.setText(suku.getNmSuku());
		
		TextView keterangan = (TextView)findViewById(R.id.ketSuku);
		keterangan.setText(Html.fromHtml(suku.getKet()));
		
		ImageView gambar2 = (ImageView) findViewById(R.id.gambarSuku);
		gambar2.setTag("http://192.168.173.1:8080/resources/"+suku.getGambarSuku());
	    new LoadImageTask(gambar2).execute();
	}
	
	class MuatSemuaTag extends AsyncTask<String, String, String>{
		@Override
		protected String doInBackground(String... args) {
			try{
				JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/suku/"+suku.getNmSuku().replace(" ", "%20"));
				Log.d("jumlah prov suku",""+larik.length());
				for(int i = 0; i < larik.length(); i++) {
					JSONObject baris = larik.getJSONObject(i);
					if(!baris.getString("nmProvinsi").equals(suku.getNmProvinsi()))
					daftarProvinsi.add(
	                		new Provinsi(
	                				baris.getInt("idProvinsi"),
	                				baris.getString("nmProvinsi"),
	                				baris.getString("keterangan")
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
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					taggingeBro.setAdapter(new tagAdapter(Page_suku_bangsa.this, daftarProvinsi));

				}
			});
		}
    }
		private class LoadImageTask extends AsyncTask<Object, Void, Bitmap> {
		 private ImageView imv;
	     private String path;
	     public LoadImageTask(ImageView imv){
	    	 this.imv = imv;
	    	 this.path = imv.getTag().toString();
	    	 }
	    @Override
		protected Bitmap doInBackground(Object... params){
	    	Bitmap bitmap = null;
			try {
				bitmap = BitmapFactory.decodeStream((InputStream)new URL(path).getContent());
			} catch (Exception e) {
			} 
			return bitmap;
		}
		@Override
		protected void onPostExecute(final Bitmap result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					imv.setImageBitmap(result);
				}
			});
			 
	    }	
	}
}