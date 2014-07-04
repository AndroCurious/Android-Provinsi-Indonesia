package com.niken.provinsiindonesia.adapter;


import com.niken.provinsiindonesia.R;
import com.niken.provinsiindonesia.entities.Menu;
import com.niken.provinsiindonesia.list.List_alat_musik;
import com.niken.provinsiindonesia.list.List_lagu_daerah;
import com.niken.provinsiindonesia.list.List_rumah_adat;
import com.niken.provinsiindonesia.list.List_suku_bangsa;
import com.niken.provinsiindonesia.list.List_tari_adat;
import com.niken.provinsiindonesia.page.Page_info_resmi;
import com.niken.provinsiindonesia.page.Page_news;
import com.niken.provinsiindonesia.tools.Gambar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class MenuAdapter extends ArrayAdapter<Menu> {
	
	 Context context;
	    int layoutResourceId;   
	    Menu data[] = null;

	public MenuAdapter(Context context, int resource,  Menu data[]) {
		super(context, resource, data);
		 this.layoutResourceId = resource;
	        this.context = context;
	        this.data = data;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
        menuHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) ((Activity) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new menuHolder();
            holder.gambar1 = (ImageView)row.findViewById(R.id.imageView1);
            holder.tombol1 = (Button)row.findViewById(R.id.button1);
            row.setTag(holder);
        }
        else
        {
            holder = (menuHolder)row.getTag();
        }
        final Menu satuan = data[position];
        holder.tombol1.setText(satuan.judul);
        holder.tombol1.setOnClickListener(new View.OnClickListener() {//fungsi yang dipanggil oleh BAGIAN MENU
            @Override
            public void onClick(View view) {
            	Intent pilihan = new Intent();
                switch(satuan.icon){
                case R.drawable.info: pilihan = new Intent(context, Page_info_resmi.class); break;
                case R.drawable.suku: pilihan = new Intent(context, List_suku_bangsa.class); break;
                case R.drawable.rumah: pilihan = new Intent(context, List_rumah_adat.class); break;
                case R.drawable.tari_icon: pilihan = new Intent(context, List_tari_adat.class); break;
                case R.drawable.alat: pilihan = new Intent(context, List_alat_musik.class); break;
                case R.drawable.lagu: pilihan = new Intent(context, List_lagu_daerah.class); break;
                case R.drawable.news: pilihan = new Intent(context, Page_news.class); break;
                }
                pilihan.putExtra("namaprovinsi", satuan.namaprovinsi);
                pilihan.putExtra("id_provinsi", satuan.idprovinsi);
                context.startActivity(pilihan); 
            }
        });
        
        
        holder.gambar1.setImageBitmap(Gambar.decodeSampledBitmapFromResource(context.getResources(), satuan.icon, 100, 100));
        return row;
    }
	public class menuHolder
    {
        ImageView gambar1;
        Button tombol1;
    }

}
