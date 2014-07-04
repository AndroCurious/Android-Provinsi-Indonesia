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
import com.niken.provinsiindonesia.entities.SeniTari;
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

public class Page_tari_adat extends Activity {
	ArrayList<Provinsi> daftarProvinsi;
	SeniTari tari;
	HorizontalListView taggingeBro ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_tari_adat);
		tari = (SeniTari)getIntent().getExtras().get("item");
		setTitle("Provinsi "+tari.getNmProvinsi());
		taggingeBro = (HorizontalListView) findViewById(R.id.daftarTag);
		daftarProvinsi = new ArrayList<Provinsi>();
		new MuatSemuaTag().execute();
		TextView namaalat = (TextView)findViewById(R.id.nmTari);
		namaalat.setText(tari+"");
		
		TextView keterangan = (TextView)findViewById(R.id.ketTari);
		keterangan.setText(Html.fromHtml(tari.getKetTari()));
		
		ImageView gambar2 = (ImageView) findViewById(R.id.gambarTari);
		gambar2.setTag("http://192.168.173.1:8080/resources/"+tari.getGambarTari());
	    new LoadImageTask(gambar2).execute();
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
	class MuatSemuaTag extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... args) {
			try{
				JSONArray larik  = JSONParser.getResultList("http://192.168.173.1:8080/api/senitari/"+tari.getNmTari().replace(" ", "%20"));
				Log.d("jumlah",""+larik.length());
				for(int i = 0; i < larik.length(); i++) {
					JSONObject baris = larik.getJSONObject(i);
					if(!baris.getString("nmProvinsi").equals(tari.getNmProvinsi()))
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
					taggingeBro.setAdapter(new tagAdapter(Page_tari_adat.this, daftarProvinsi));
				}
			});
		}
    }
}