package com.niken.provinsiindonesia.entities;

import java.io.Serializable;

public class LaguDaerah implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -5155183682039394165L;
	private String nmProvinsi;
private int idLaguDaerah;
private String nmLaguDaerah;
private String laguDaerah;
private String linkLagudaerah;

public LaguDaerah(String nmProvinsi, int idLaguDaerah, String nmLaguDaerah, String laguDaerah,
		String linkLagudaerah) {
	this.nmProvinsi = nmProvinsi;
	this.idLaguDaerah = idLaguDaerah;
	this.nmLaguDaerah = nmLaguDaerah;
	this.laguDaerah = laguDaerah;
	this.linkLagudaerah = linkLagudaerah;
}

public String getNmProvinsi(){
	return nmProvinsi;
}
public int getIdLaguDaerah() {
	return idLaguDaerah;
}
public String getNmLaguDaerah() {
	return nmLaguDaerah;
}
public String getLaguDaerah() {
	return laguDaerah;
}
public String getLinkLagudaerah() {
	return linkLagudaerah;
}

@Override
public String toString(){
	return nmLaguDaerah;
}

}
