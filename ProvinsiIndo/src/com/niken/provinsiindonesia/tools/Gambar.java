package com.niken.provinsiindonesia.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Gambar {
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);
	
	    options.inSampleSize  = 1;
	    if (options.outHeight > reqHeight || options.outWidth > reqWidth) {
	        final int halfHeight = options.outHeight / 2;
	        final int halfWidth = options.outWidth / 2;
	        while ((halfHeight / options.inSampleSize) > reqHeight
	                && (halfWidth / options.inSampleSize) > reqWidth) {
	        	options.inSampleSize *= 2;
	        }
	    }
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
}
