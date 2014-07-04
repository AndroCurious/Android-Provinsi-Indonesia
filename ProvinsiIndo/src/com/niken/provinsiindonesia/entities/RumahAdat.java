package com.niken.provinsiindonesia.entities;

import java.io.Serializable;

public class RumahAdat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2335276934925086540L;
	private String nmProvinsi;
	private int idRumah;
	private String nmRumah;
	private String gambarRmh;
	private String ketRumah;
	
	public RumahAdat(String nmProvinsi, int idRumah, String nmRumah, String gambarRmh,
			String ketRumah) {
		this.nmProvinsi = nmProvinsi;
		this.idRumah = idRumah;
		this.nmRumah = nmRumah;
		this.gambarRmh = gambarRmh;
		this.ketRumah = ketRumah;
	}
	public String getNmProvinsi(){
		return nmProvinsi;
	}

	public int getIdRumah() {
		return idRumah;
	}
	public String getNmRumah() {
		return nmRumah;
	}
	public String getGambarRmh() {
		return gambarRmh;
	}
	public String getKetRumah() {
		return ketRumah;
	}
	
	@Override
	public String toString(){
		return nmRumah;
	}


}
