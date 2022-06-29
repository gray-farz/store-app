package com.example.rezakala.Action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.rezakala.MainActivity;

public class Getinfo {
    private static final String TAG = "aaa";
    public  static void getinfo(Activity activity){

        SharedPreferences sharedPreferences=activity.getSharedPreferences("info",0);
        String toekn=sharedPreferences.getString("token",null);
        if(toekn==null){
            Log.d(TAG, "getinfo: toekn==null");
        }else
        {
//            Intent intent=new Intent(activity,Main_profile_panel.class);
//            activity.startActivity(intent);
//            activity.finish();

        }
    }

    public  static void Getexit(Activity activity){

        SharedPreferences sharedPreferences=activity.getSharedPreferences("info",0);
        String toekn=sharedPreferences.getString("token",null);
        if(toekn==null){
            Intent intent=new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
//
//    public  static void Getexit(Context content){
//
//        SharedPreferences sharedPreferences=content.getSharedPreferences("info",0);
//        String toekn=sharedPreferences.getString("token",null);
//        if(toekn==null){
//            Intent intent=new Intent(content,MainActivity.class);
//            content.startActivity(intent);
//            ((Activity)content).finish();
//        }
//    }
}
