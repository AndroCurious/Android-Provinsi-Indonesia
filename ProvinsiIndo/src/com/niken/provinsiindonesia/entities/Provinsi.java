package com.niken.provinsiindonesia.entities;

public class Provinsi {
	private Integer idProvinsi;
    private String nmProvinsi;
    private String keterangan;
	public Provinsi(Integer idProvinsi, String nmProvinsi, String keterangan) {
		super();
		this.idProvinsi = idProvinsi;
		this.nmProvinsi = nmProvinsi;
		this.keterangan = keterangan;
	}
	public Integer getIdProvinsi() {
		return idProvinsi;
	}
	public String getNmProvinsi() {
		return nmProvinsi;
	}
	public String getKeterangan() {
		return keterangan;
	}
    
    

}
