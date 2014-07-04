package com.niken.provinsiindonesia;

import com.niken.provinsiindonesia.adapter.MenuAdapter;
import com.niken.provinsiindonesia.entities.Menu;
import android.app.ListActivity;
import android.os.Bundle;

public class MuatProvinsi extends ListActivity  {
		@Override
	protected void onCreate(Bundle pesan) { 
		super.onCreate(pesan);
		String namaProv = (String) getIntent().getExtras().get("namaprovinsi");
		setTitle(namaProv);
		setContentView(R.layout.kosongan);
		String id_provinsi =(String) getIntent().getExtras().get("id_provinsi");
		setListAdapter(new MenuAdapter(this, R.layout.satuanmenu, new Menu[]
				{
				new Menu(R.drawable.info, "Info Resmi", id_provinsi, namaProv), 
				new Menu(R.drawable.suku, "Suku Bangsa", id_provinsi, namaProv),
				new Menu(R.drawable.rumah, "Rumah Adat",id_provinsi, namaProv),
		    	new Menu(R.drawable.tari_icon, "Tari Adat",id_provinsi, namaProv),
		    	new Menu(R.drawable.alat, "Alat Musik",id_provinsi, namaProv),
		    	new Menu(R.drawable.lagu, "Lagu Daerah",id_provinsi, namaProv),
		    	new Menu(R.drawable.news, "News",id_provinsi, namaProv),
		    }));
	}
}