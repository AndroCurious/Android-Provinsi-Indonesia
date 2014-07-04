package com.niken.provinsiindonesia.entities;

public class Menu {
	public int icon;
    public String judul;
    public String idprovinsi;
    public String namaprovinsi;
    public Menu(){
    }
   
    public Menu(int icon, String judul, String idprovinsi, String namaprovinsi) {
        this.icon = icon;
        this.judul = judul;
        this.idprovinsi = idprovinsi;
        this.namaprovinsi = namaprovinsi;
    }
}
