package com.xpectra.tvmaze.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xpectra on 14/7/2016.
 */
public class Utils {

    /********************************************************************************
     * Global variables                                                             *
     ********************************************************************************/
    private static Gson mGsonBuilder;
    private static String formatDateTime = "yyyy-MM-dd'T'HH:mm:ss";


    /********************************************************************************
     * Get Gson Builder                                                             *
     ********************************************************************************/
    public static Gson getGsonBuilder() {
        if (mGsonBuilder == null) {
            return new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
        }

        return mGsonBuilder;
    }

    public static String parseMinHoras(int min){
        int hours = min / 60;
        int minutes = min % 60;

        if(minutes>0)
            return hours+"hrs y "+minutes+"min";
        else
            return hours+"hrs";
    }

    public static String getListString(List<String> days){

        String result = "";

        for (int i=0;i<days.size();i++){
            if(i>0)
                result+= ", "+days.get(i);
            else
                result=days.get(i);
        }

        return result;
    }

    public static String parseHours(String time){

        if(!Locale.getDefault().getLanguage().equals("en")) {

            try {
                final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                final Date dateObj = sdf.parse(time);

                return new SimpleDateFormat("hh:mm a").format(dateObj);
            } catch (final ParseException e) {
                e.printStackTrace();
            }
        }

        return time;
    }

}
