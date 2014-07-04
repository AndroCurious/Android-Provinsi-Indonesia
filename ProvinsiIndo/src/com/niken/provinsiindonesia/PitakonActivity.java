package com.niken.provinsiindonesia;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import com.niken.provinsiindonesia.list.List_alat_musik;
import com.niken.provinsiindonesia.tools.JSONParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.view.View;

public class PitakonActivity extends Activity {

	private ProgressDialog pDialog;
	EditText pertanyaan = null;
	RadioButton jawabanA = null;
	RadioButton jawabanB = null;
	RadioButton jawabanC = null;
	RadioButton jawabanD = null;
	RadioGroup kelompokJawaban = null;
	
	int indeksSoal = 0;
	int soalSingUwesKejawab[] = null;
	int jawabanSingBener[] = null;
	boolean pembenaran =false;
	Button tombolSebelum, tombolBerikut = null;
	static JSONArray daftarTanya = null;

	@Override
	public void onCreate(Bundle catetan) {
		super.onCreate(catetan);	
		setContentView(R.layout.pitakon);
			pertanyaan = (EditText) findViewById(R.id.pertanyaan);
			jawabanA = (RadioButton) findViewById(R.id.satu);
			jawabanB = (RadioButton) findViewById(R.id.dua);
			jawabanC = (RadioButton) findViewById(R.id.tiga);
			jawabanD = (RadioButton) findViewById(R.id.empat);
			kelompokJawaban = (RadioGroup) findViewById(R.id.jawabans);
			new MuatSemuaSoal().execute();
	}

	private void simpenJawabaneNangArray(){
		//set jawabane sing dipilih
		if (jawabanA.isChecked())
			soalSingUwesKejawab[indeksSoal] = 0;
		if (jawabanB.isChecked())
			soalSingUwesKejawab[indeksSoal] = 1;
		if (jawabanC.isChecked())
			soalSingUwesKejawab[indeksSoal] = 2;
		if (jawabanD.isChecked())
			soalSingUwesKejawab[indeksSoal] = 3;
		//
	}

	private void jedhulnePitakonane(int indeksPitakon,boolean pembenaran) {
		try {
			JSONObject satuanPertanyaan = daftarTanya.getJSONObject(indeksPitakon);//dapatkan objek soal dari JSON

			//dapatkan field Pertanyaan dari Objek Soal
			String teksSoal = satuanPertanyaan.getString("pertanyaan");
			pertanyaan.setText(teksSoal.toCharArray(), 0, teksSoal.length());

			kelompokJawaban.clearCheck(); //bersihkan semua pilihan radio
			//warna teks diputihkan semua
			jawabanA.setTextColor(Color.WHITE); 
			jawabanB.setTextColor(Color.WHITE);
			jawabanC.setTextColor(Color.WHITE);
			jawabanD.setTextColor(Color.WHITE);

			//dapatkan LARIK Jawabans Objek Soal
			JSONArray larikJawaban = satuanPertanyaan.getJSONArray("jawabans");

			//dapatkan Objek Jawaban A
			String satuanPilihan = larikJawaban.getJSONObject(0).getString("jawaban");
			//set teks jawaban A
			jawabanA.setText(satuanPilihan.toCharArray(), 0, satuanPilihan.length());
			
			//dapatkan Objek Jawaban B
			satuanPilihan = larikJawaban.getJSONObject(1).getString("jawaban");
			//set teks jawaban B
			jawabanB.setText(satuanPilihan.toCharArray(), 0, satuanPilihan.length());
			
			//dapatkan Objek Jawaban C
			satuanPilihan = larikJawaban.getJSONObject(2).getString("jawaban");
			//set teks jawaban C
			jawabanC.setText(satuanPilihan.toCharArray(), 0, satuanPilihan.length());
			
			//dapatkan Objek Jawaban D
			satuanPilihan = larikJawaban.getJSONObject(3).getString("jawaban");
			//set teks jawaban D
			jawabanD.setText(satuanPilihan.toCharArray(), 0, satuanPilihan.length());			
			
			//mengeset soal sing uwes pernah kejawab
			if (soalSingUwesKejawab[indeksPitakon] == 0)
				kelompokJawaban.check(R.id.satu);
			if (soalSingUwesKejawab[indeksPitakon] == 1)
				kelompokJawaban.check(R.id.dua);
			if (soalSingUwesKejawab[indeksPitakon] == 2)
				kelompokJawaban.check(R.id.tiga);
			if (soalSingUwesKejawab[indeksPitakon] == 3)
				kelompokJawaban.check(R.id.empat);
			//mengeset soal sing uwes pernah kejawab

			//mengeset ulang judul halaman
			setTitle("Latihan Soal     " + (indeksSoal+1)+ " dari " + daftarTanya.length());
			
			//jika indeksSoal paling akhir, maka tombol berikutnya dipateni
			if (indeksSoal == (daftarTanya.length()-1)) 
				tombolBerikut.setEnabled(false);
			
			//jika indeksSoal ijek awalan, maka tombol sebelumnya dipateni
			if (indeksSoal == 0)
				tombolSebelum.setEnabled(false);

			//jika indeksSoal luweh gedhe seko enol, maka tombol sebelumnya diempakne
			if (indeksSoal > 0)
				tombolSebelum.setEnabled(true);
			
			//jika indeksSoal kurang dari paling keri, maka tombol berikute diempakne
			if (indeksSoal < (daftarTanya.length()-1))
				tombolBerikut.setEnabled(true);

			//mode pembenaran itu true
			if (pembenaran) {//maka

				//jawabane sing salah2 diabangi
				if (soalSingUwesKejawab[indeksPitakon] != jawabanSingBener[indeksPitakon]) {
					if (soalSingUwesKejawab[indeksPitakon] == 0)
						jawabanA.setTextColor(Color.RED);
					if (soalSingUwesKejawab[indeksPitakon] == 1)
						jawabanB.setTextColor(Color.RED);
					if (soalSingUwesKejawab[indeksPitakon] == 2)
						jawabanC.setTextColor(Color.RED);
					if (soalSingUwesKejawab[indeksPitakon] == 3)
						jawabanD.setTextColor(Color.RED);
				}

				//jawabane sing bener diwarnai ijo
				if (jawabanSingBener[indeksPitakon] == 0)
					jawabanA.setTextColor(Color.GREEN);
				if (jawabanSingBener[indeksPitakon] == 1)
					jawabanB.setTextColor(Color.GREEN);
				if (jawabanSingBener[indeksPitakon] == 2)
					jawabanC.setTextColor(Color.GREEN);
				if (jawabanSingBener[indeksPitakon] == 3)
					jawabanD.setTextColor(Color.GREEN);
			}
		} catch (Exception e) {
		}
	}
	
	private class MuatSemuaSoal extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PitakonActivity.this);
            pDialog.setMessage("Memuat semua soal!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			try{
				daftarTanya = JSONParser.getResultList("http://192.168.173.1:8080/api/soal");
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
					try{
						soalSingUwesKejawab = new int[daftarTanya.length()]; //larik soalSingUwesKejawab length'nya = jumlah Tanya
						Arrays.fill(soalSingUwesKejawab, -1);//larik soalSingUwesKejawab diberi nilai -1 semuanya, -1 penanda masih pertama kali

						jawabanSingBener = new int[daftarTanya.length()]; //larik jawabanSingBener length'nya = jumlah Tanya
						//memberi nilai tiap item jawabanSingBener dari larik JSON
						for(int x=0; x<daftarTanya.length(); x++)
							jawabanSingBener[x] = daftarTanya.getJSONObject(x).getInt("indeksJawabanBenar");

						jedhulnePitakonane(0,pembenaran);

						tombolSebelum = (Button)findViewById(R.id.Sebelum);
						tombolSebelum.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								simpenJawabaneNangArray();
								indeksSoal--;
								if (indeksSoal < 0)
									indeksSoal = 0;
								jedhulnePitakonane(indeksSoal,pembenaran);
							}
						});

						tombolBerikut = (Button)findViewById(R.id.Sesudah);
						tombolBerikut.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								simpenJawabaneNangArray();
								indeksSoal++;
								if (indeksSoal >= daftarTanya.length())
									indeksSoal = daftarTanya.length() - 1;
								jedhulnePitakonane(indeksSoal,pembenaran);
							}
						});

						((Button)findViewById(R.id.Selesai)).setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								simpenJawabaneNangArray();
								
								//ngitung bejine
								int bejine = 0;
								for(int i=0; i<jawabanSingBener.length; i++){
									if ((jawabanSingBener[i] != -1) && (jawabanSingBener[i] == soalSingUwesKejawab[i]))
										bejine++;
								}
								
								new AlertDialog.Builder(PitakonActivity.this)
								.setTitle("Skor")
								.setMessage("Bener "+(bejine) +" dari " + (daftarTanya.length()))
								.setNegativeButton("Keluar", new DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog, int which) {
										pembenaran = false;
										finish();
									}
								})
								.setNeutralButton("Ulangi", new DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog, int which) {
										pembenaran = false;
										indeksSoal=0;
										//semua jawabane dikosongke kwabeh disek
										Arrays.fill(soalSingUwesKejawab, -1);
										PitakonActivity.this.jedhulnePitakonane(0, pembenaran);
									}
								})
								.setPositiveButton("Koreksi", new DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog, int which) {
										pembenaran = true;
										indeksSoal=0;
										PitakonActivity.this.jedhulnePitakonane(0, pembenaran);
									}
								}).show();
							}
						});
					}
					catch(Exception ex){
					}
				}
			});
		}		
	}
}