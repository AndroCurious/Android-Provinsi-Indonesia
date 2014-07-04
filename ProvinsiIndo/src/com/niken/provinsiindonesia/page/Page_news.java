package com.niken.provinsiindonesia.page;

import org.json.JSONObject;
import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.entities.News;
import com.niken.provinsiindonesia.tools.JSONParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Page_news extends Activity {
	private ProgressDialog pDialog;
	private String id_provinsi; 
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_news);
		setTitle((String) getIntent().getExtras().get("namaprovinsi"));
		id_provinsi = (String) getIntent().getExtras().get("id_provinsi");
		webView = (WebView) findViewById(R.id.berita);
		webView.setWebViewClient(new WebViewClient());
		new MuatHalamanInfo().execute();
	}
	
	class MuatHalamanInfo extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Page_news.this);
            pDialog.setMessage("Memuat berita...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... args) {
			try{
				JSONObject baris  = JSONParser.getSingleResult("http://192.168.173.1:8080/api/provinsi/"+id_provinsi+"/news");
				berita = new News(
	                				baris.getInt("idNews"),
	                				baris.getString("isinews")
	                				);
	                				Log.d("cobaisinumber: ",String.valueOf(berita.getIdNews()));
	                				Log.d("cobaisi: ",berita.getIsinews());
			}catch(Exception ex){
			}
			return null;
		}
		private News berita;
		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					try{
					webView.getSettings().setJavaScriptEnabled(true);
					webView.getSettings().setPluginState(PluginState.ON);
					webView.loadUrl("http:///"+berita.getIsinews());
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}});
		}
    }

}
