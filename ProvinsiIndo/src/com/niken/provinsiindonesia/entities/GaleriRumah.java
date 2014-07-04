package com.niken.provinsiindonesia.entities;

public class GaleriRumah {
private int idGalerirumah;
private String namaGalerirmh;
private String gambarGalerirmh;

public GaleriRumah(int idGalerirumah, String namaGalerirmh,
		String gambarGalerirmh) {
	super();
	this.idGalerirumah = idGalerirumah;
	this.namaGalerirmh = namaGalerirmh;
	this.gambarGalerirmh = gambarGalerirmh;
}
public int getIdGalerirumah() {
	return idGalerirumah;
}
public String getNamaGalerirmh() {
	return namaGalerirmh;
}
public String getGambarGalerirmh() {
	return gambarGalerirmh;
}


}
