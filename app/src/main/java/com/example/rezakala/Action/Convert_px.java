package com.example.rezakala.Action;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Convert_px {
    public static int convertpx(float px, Context context){
        Resources resources=context.getResources();
        DisplayMetrics metrics=resources.getDisplayMetrics();
        float dp= px /((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT );
       return (int)dp;
    }

    public static int Convert_px(float px,Context context){
        float t=px*context.getResources().getDisplayMetrics().density;
        return (int)t;
    }

}
