package com.example.rezakala.Action;

import android.content.Context;
import android.content.SharedPreferences;

public class GetToken {
    public static String gettoken(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
         String toekn=sharedPreferences.getString("token",null);
         if (toekn==null)
             return "135t";
             return  toekn;
    }
}
