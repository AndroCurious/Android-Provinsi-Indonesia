package com.niken.provinsiindonesia.entities;

import java.io.Serializable;

public class SeniTari implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5289170267272305015L;
	private String nmProvinsi;
	private int idTari;
	private String nmTari;
	private String gambarTari;
	private String ketTari;
	
	
	public SeniTari(String nmProvinsi, int idTari, String nmTari, String gambarTari, String ketTari) {
		this.nmProvinsi = nmProvinsi;
		this.idTari = idTari;
		this.nmTari = nmTari;
		this.gambarTari = gambarTari;
		this.ketTari = ketTari;
	}
	public String getNmProvinsi(){
		return nmProvinsi;
	}
	public int getIdTari() {
		return idTari;
	}
	public String getNmTari() {
		return nmTari;
	}
	public String getGambarTari() {
		return gambarTari;
	}
	public String getKetTari() {
		return ketTari;
	}
	
	@Override
	public String toString(){
		return nmTari;
	}

	
}
