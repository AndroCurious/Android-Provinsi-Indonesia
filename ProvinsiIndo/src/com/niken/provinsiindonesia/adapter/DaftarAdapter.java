package com.niken.provinsiindonesia.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.niken.provinsiindonesia.Pencarian;
import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.entities.AlatMusik;
import com.niken.provinsiindonesia.entities.LaguDaerah;
import com.niken.provinsiindonesia.entities.RumahAdat;
import com.niken.provinsiindonesia.entities.SeniTari;
import com.niken.provinsiindonesia.entities.Suku;
import com.niken.provinsiindonesia.list.List_alat_musik;
import com.niken.provinsiindonesia.list.List_rumah_adat;
import com.niken.provinsiindonesia.list.List_suku_bangsa;
import com.niken.provinsiindonesia.list.List_tari_adat;
import com.niken.provinsiindonesia.page.Page_Lagu_daerah;
import com.niken.provinsiindonesia.page.Page_alat_musik;
import com.niken.provinsiindonesia.page.Page_rumah_adat;
import com.niken.provinsiindonesia.page.Page_suku_bangsa;
import com.niken.provinsiindonesia.page.Page_tari_adat;
import com.niken.provinsiindonesia.tools.Gambar;

public class DaftarAdapter extends ArrayAdapter<Object> {
	public DaftarAdapter(Context context, List<Object> objects) {
		super(context, R.layout.satuan_list, objects);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.satuan_list, parent, false);
			holder = new Holder();
			holder.gambar = (ImageView) convertView.findViewById(R.id.idne1);
			holder.teks = (TextView) convertView.findViewById(R.id.idne2);
			holder.tombol = (Button) convertView.findViewById(R.id.idne3);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		int gambar = getContext() instanceof List_alat_musik ? R.drawable.alat
				: getContext() instanceof Pencarian ? R.drawable.alat
						: getContext() instanceof List_suku_bangsa ? R.drawable.suku
								: getContext() instanceof List_rumah_adat ? R.drawable.rumah
										: getContext() instanceof List_tari_adat ? R.drawable.tari_icon
												: R.drawable.lagu;

		holder.gambar.setImageBitmap(Gambar.decodeSampledBitmapFromResource(
				getContext().getResources(), gambar, 100, 100));
		holder.teks.setText("" + getItem(position));
		holder.tombol.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent luncurkan = new Intent(
						getContext(),
						getContext() instanceof List_alat_musik ? Page_alat_musik.class
								: getContext() instanceof Pencarian ? Page_alat_musik.class
										: getContext() instanceof List_suku_bangsa ? Page_suku_bangsa.class
												: getContext() instanceof List_rumah_adat ? Page_rumah_adat.class
														: getContext() instanceof List_tari_adat ? Page_tari_adat.class
																: Page_Lagu_daerah.class);

				luncurkan
						.putExtra(
								"item",
								getContext() instanceof List_alat_musik ? (AlatMusik) getItem(position)
										: getContext() instanceof Pencarian ? (AlatMusik) getItem(position)
											: getContext() instanceof List_suku_bangsa ? (Suku) getItem(position)
												: getContext() instanceof List_rumah_adat ? (RumahAdat) getItem(position)
														: getContext() instanceof List_tari_adat ? (SeniTari) getItem(position)
																: (LaguDaerah) getItem(position));
				getContext().startActivity(luncurkan);
			}
		});
		return convertView;
	}

	// ben gak golek2i maning broh
	private static class Holder {
		public ImageView gambar;
		public TextView teks;
		public Button tombol;
	}

}
