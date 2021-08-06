package com.example.mysocialmedia.utils;

import android.content.Context;
import android.widget.Toast;


public class Utils {
    public static String formatDate(String date){
        String[] split = date.split("\\.");
        return split[0].replace('T' , ' ');
    }
    public static void makeToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
