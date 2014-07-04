package com.niken.provinsiindonesia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.niken.provinsiindonesia.pulau.Kep_maluku;
import com.niken.provinsiindonesia.pulau.Kep_nusa_tenggara;
import com.niken.provinsiindonesia.pulau.Pulau_Sulawesi;
import com.niken.provinsiindonesia.pulau.Pulau_Sumatera;
import com.niken.provinsiindonesia.pulau.Pulau_jawa;
import com.niken.provinsiindonesia.pulau.Pulau_kalimantan;
import com.niken.provinsiindonesia.pulau.Pulau_papua;
import com.niken.provinsiindonesia.tools.ColorTool;
import com.niken.provinsiindonesia.tools.Gambar;

public class MainActivity extends Activity implements View.OnTouchListener {
	private ImageView iv;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        iv=(ImageView) findViewById(R.id.image);
        iv.setOnTouchListener(this);
    }
    
    public boolean onTouch(View v, MotionEvent ev) {
		boolean handledHere = false;
		final int action = ev.getAction();
		final int evX = (int) ev.getX();
		final int evY = (int) ev.getY();
		int nextImage = -1; 
		Integer tagNum = (Integer) iv.getTag();
		int currentResource = (tagNum == null) ? R.drawable.peta_bg2 : tagNum.intValue();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (currentResource == R.drawable.peta_bg2) {
				nextImage = R.drawable.peta_bg2;
				handledHere = true;
			} else
				handledHere = true;
			break;
		case MotionEvent.ACTION_UP:
			int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
			ColorTool ct = new ColorTool();
			int tolerance = 25;
			nextImage = R.drawable.peta_bg2;
			if (ct.closeMatch(Color.BLUE, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Pulau_Sulawesi.class));
			}
			else if (ct.closeMatch(Color.GREEN, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Kep_maluku.class));
			}
			else if (ct.closeMatch(Color.CYAN, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Pulau_papua.class));
			} else if (ct.closeMatch(Color.GRAY, touchColor, tolerance)) {
				Toast.makeText(getBaseContext(), "tekan pada gambar pulau",Toast.LENGTH_SHORT).show();
			} else if (ct.closeMatch(Color.MAGENTA, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Pulau_kalimantan.class));
			} else if (ct.closeMatch(Color.BLACK, touchColor, tolerance)) {
				Toast.makeText(getBaseContext(), "tekan pada gambar pulau",Toast.LENGTH_SHORT).show();
			} else if (ct.closeMatch(Color.RED, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Pulau_jawa.class));
			} else if (ct.closeMatch(Color.DKGRAY, touchColor, tolerance)) {
				Toast.makeText(getBaseContext(), "tekan pada gambar pulau",Toast.LENGTH_SHORT).show();
			} else if (ct.closeMatch(Color.LTGRAY, touchColor, tolerance)) {
				Toast.makeText(getBaseContext(), "tekan pada gambar pulau",Toast.LENGTH_SHORT).show();
			}
			else if (ct.closeMatch(Color.WHITE, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Kep_nusa_tenggara.class));
			}
			else if (ct.closeMatch(Color.YELLOW, touchColor, tolerance)) {
				startActivity (new Intent (getApplicationContext(),Pulau_Sumatera.class));
			}
			if (currentResource == nextImage) {
				nextImage = R.drawable.peta_bg2;
			}
			handledHere = true;
			break;
		default:
			handledHere = false;
		} 
		if (handledHere) {

			if (nextImage > 0) {
				iv.setImageBitmap(Gambar.decodeSampledBitmapFromResource(getResources(), nextImage, 200, 200));
				iv.setTag(nextImage);
			}
		}
		return handledHere;
	}

	public int getHotspotColor(int hotspotId, int x, int y) {
		ImageView img = (ImageView) findViewById(hotspotId);
		if (img == null) {
			return 0;
		} else {
			img.setDrawingCacheEnabled(true);
			Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
			if (hotspots == null) {
				img = null;
				return 0;
			} else {
				img.setDrawingCacheEnabled(false);
				int pix = hotspots.getPixel(x, y);
				img = null;
				hotspots.recycle();
				hotspots = null;
				return pix;
			}
			
		}
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
		.setTitle("Konfirmasi Keluar Aplikasi")
		.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dlg, int sumthin) {
			}
		})
		.setPositiveButton("Ya",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dlg, int sumthin) {
					 finish();
			            System.exit(0);
				}
			}).show();	
	}

	public void tentangku(View view){
		startActivity (new Intent (getApplicationContext(),Tentangku.class));
	}
	
	public void latihan(View view){
		startActivity(new Intent(getApplicationContext(), PitakonActivity.class));
	}
	
	public void pencarian(View view){
		startActivity(new Intent(getApplicationContext(), Pencarian.class));
	}
}