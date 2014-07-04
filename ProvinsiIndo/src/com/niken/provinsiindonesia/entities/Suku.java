package com.niken.provinsiindonesia.entities;

import java.io.Serializable;

public class Suku implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 2284305517729701023L;
private String nmProvinsi;
	private int	idSuku;
private String nmSuku;
private String gambarSuku;
private String ket;


public Suku(String nmProvinsi,int idSuku, String nmSuku, String gambarSuku, String ket) {
	this.nmProvinsi = nmProvinsi;
	this.idSuku = idSuku;
	this.nmSuku = nmSuku;
	this.gambarSuku = gambarSuku;
	this.ket = ket;
}

public String getNmProvinsi(){
	return nmProvinsi;
}

public int getIdSuku() {
	return idSuku;
}
public String getNmSuku() {
	return nmSuku;
}
public String getGambarSuku() {
	return gambarSuku;
}
public String getKet() {
	return ket;
}
@Override
public String toString(){
	return nmSuku;
}

}
