package com.niken.provinsiindonesia.entities;

import java.io.Serializable;

public class AlatMusik implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -5307698876476100998L;
	private String nmProvinsi;
private int idAlatmusik;
private String nmAlat;
private String gambarMusik;
private String ketAlat;



public AlatMusik(String nmProvinsi, int idAlatMusik, String nmAlat, String gambarMusik,String ketAlat) {
	this.nmProvinsi = nmProvinsi;
	this.idAlatmusik = idAlatMusik;
	this.nmAlat = nmAlat;
	this.gambarMusik = gambarMusik;
	this.ketAlat = ketAlat;
}

public String getNmProvinsi(){
	return nmProvinsi;
}

public int getIdAlatmusik() {
	return idAlatmusik;
}
public String getNmAlat() {
	return nmAlat;
}
public String getGambarMusik() {
	return gambarMusik;
}
public String getKetAlat() {
	return ketAlat;
}
@Override
public String toString(){
	return nmAlat;
}

}
