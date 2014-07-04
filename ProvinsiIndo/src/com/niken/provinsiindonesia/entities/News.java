package com.niken.provinsiindonesia.entities;

public class News {
	 private int idNews;
	 private String isinews;
	public News(int idNews, String isinews) {
		super();
		this.idNews = idNews;
		this.isinews = isinews;
	}
	public int getIdNews() {
		return idNews;
	}
	public String getIsinews() {
		return isinews;
	}
	 
}
