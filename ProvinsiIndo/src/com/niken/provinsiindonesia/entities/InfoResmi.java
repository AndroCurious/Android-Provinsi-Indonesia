package com.niken.provinsiindonesia.entities;

public class InfoResmi {
	private int idInfo;
	private String gambarPeta;
	private String keteranganinfo;
	public InfoResmi(int idInfo, String gambarPeta, String keteranganinfo) {
		super();
		this.idInfo = idInfo;
		this.gambarPeta = gambarPeta;
		this.keteranganinfo = keteranganinfo;
	}
	public int getIdInfo() {
		return idInfo;
	}
	public String getGambarPeta() {
		return gambarPeta;
	}
	public String getKeteranganinfo() {
		return keteranganinfo;
	}
	
	
}
