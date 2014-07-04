package com.niken.provinsiindonesia.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.entities.Provinsi;
import com.niken.provinsiindonesia.list.List_alat_musik;
import com.niken.provinsiindonesia.list.List_rumah_adat;
import com.niken.provinsiindonesia.list.List_suku_bangsa;
import com.niken.provinsiindonesia.list.List_tari_adat;
import com.niken.provinsiindonesia.page.Page_alat_musik;
import com.niken.provinsiindonesia.page.Page_rumah_adat;
import com.niken.provinsiindonesia.page.Page_suku_bangsa;
public class tagAdapter extends ArrayAdapter<Provinsi>  {
	
	public tagAdapter(Context context, List<Provinsi> objects) {
		super(context, R.layout.satuan_tagging, objects);
	}
	
	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
		MusikHolder  holder;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.satuan_tagging, parent, false);
			holder = new MusikHolder();
			holder.tombolTag = (Button) convertView.findViewById(R.id.taggere);
			convertView.setTag(holder);
		}
		else{
			holder = (MusikHolder)convertView.getTag();
		}
		final Provinsi skrg = getItem(position);
		holder.tombolTag.setText(skrg.getNmProvinsi());
		
		holder.tombolTag.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Context konteks = getContext();
				Intent luncurkan = new Intent(
						konteks, 
						konteks instanceof Page_alat_musik ? List_alat_musik.class : 
						konteks instanceof Page_suku_bangsa ?	List_suku_bangsa.class :
						konteks instanceof Page_rumah_adat ? List_rumah_adat.class :
						List_tari_adat.class ); 
				luncurkan.putExtra("id_provinsi", String.valueOf(skrg.getIdProvinsi()));
				konteks.startActivity(luncurkan);
			}
		});
		return convertView;
	}
	
	//ini lho biar nggak mencari lagi
	private static class MusikHolder {
        public Button tombolTag;
    }

}
