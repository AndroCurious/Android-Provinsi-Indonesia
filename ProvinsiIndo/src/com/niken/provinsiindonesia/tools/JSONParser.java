package com.niken.provinsiindonesia.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Scanner;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    public static JSONArray getResultList(String url) throws IllegalStateException, ClientProtocolException, JSONException, IOException, URISyntaxException {
    	HttpGet permintaan =new HttpGet(url);
    	permintaan.setHeader("Accept", "application/json");
    	InputStream balasan = new DefaultHttpClient().execute(permintaan).getEntity().getContent();
    	Scanner baca = new Scanner(balasan);
    	String kembali = baca.useDelimiter("\\A").next();
    	baca.close();
    	return new JSONArray(kembali);
    }
    public static JSONObject getSingleResult(String url) throws IllegalStateException, ClientProtocolException, JSONException, IOException, URISyntaxException {
    	HttpGet permintaan =new HttpGet(url);
    	permintaan.setHeader("Accept", "application/json");
    	InputStream balasan = new DefaultHttpClient().execute(permintaan).getEntity().getContent();
    	Scanner baca = new Scanner(balasan);
    	String kembali = baca.useDelimiter("\\A").next();
    	baca.close();
    	return new JSONObject(kembali);
    }
}