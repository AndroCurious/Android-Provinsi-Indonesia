package com.niken.provinsiindonesia.page;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.entities.LaguDaerah;
import com.niken.provinsiindonesia.tools.Gambar;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Page_Lagu_daerah extends Activity {
	
	//cara menghentikan musik ketika ditekan tombol back
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		laguneSingDiPuter.pause();
		laguneSingDiPuter.seekTo(0);
		finish();
	}

	private LaguDaerah lagu;
	private MediaPlayer laguneSingDiPuter;
	private boolean putarKah = false; 
	private ImageButton tombolPutarPause, tombolHenti ;
	private SeekBar majuMundur;
	Handler seekHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle keadaan) {
		super.onCreate(keadaan);
		setContentView(R.layout.page_lagu_daerah1);

		lagu  = (LaguDaerah)getIntent().getExtras().get("item");
		setTitle("Provinsi "+lagu.getNmProvinsi());
		((TextView)findViewById(R.id.namaLagu)).setText(lagu.getNmLaguDaerah());

		laguneSingDiPuter = new MediaPlayer();
		laguneSingDiPuter.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
	    	laguneSingDiPuter.setDataSource("http://192.168.173.1:8080/resources/"+lagu.getLaguDaerah().replace(" ", "%20"));
	    	laguneSingDiPuter.prepare();
	    	} catch (Exception e){
	    	}
		majuMundur = (SeekBar)findViewById(R.id.gesek);
		majuMundur.setMax(laguneSingDiPuter.getDuration());
		majuMundur.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
		            laguneSingDiPuter.seekTo(progress);
		        }
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
		});
		tombolPutarPause = (ImageButton)findViewById(R.id.putar);
		tombolPutarPause.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(putarKah)
				{
					Toast.makeText(getApplicationContext(), "Mempause lagu", Toast.LENGTH_SHORT).show();
					laguneSingDiPuter.pause();
					tombolPutarPause.setImageBitmap(Gambar.decodeSampledBitmapFromResource(getResources(), R.drawable.play, 100, 100));
					putarKah = false;
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Memutar "+lagu.getNmLaguDaerah(),Toast.LENGTH_SHORT).show();
					laguneSingDiPuter.start();
					tombolPutarPause.setImageBitmap(Gambar.decodeSampledBitmapFromResource(getResources(),R.drawable.pause,100,100));
					putarKah = true;
				}
			}
		});
		tombolHenti = (ImageButton)findViewById(R.id.berhenti);
		tombolHenti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				laguneSingDiPuter.pause();
				laguneSingDiPuter.seekTo(0);
			}
		});
		rubahLaju();
		}

	  @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
		  getMenuInflater().inflate(R.menu.main, menu);
		  return true;
	   }

	   	   
	   Runnable jalankan = new Runnable() { @Override public void run() { rubahLaju(); } };
	   private void rubahLaju() { 
		   majuMundur.setProgress(laguneSingDiPuter.getCurrentPosition()); 
		   seekHandler.postDelayed(jalankan, 1000);
		   }

}