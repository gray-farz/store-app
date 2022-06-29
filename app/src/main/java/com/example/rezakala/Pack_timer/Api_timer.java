package com.example.rezakala.Pack_timer;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class Api_timer implements Config {
    static int timer[];
    static int zero=0;
    private static final String TAG = "aaa";

    public static void Getmethod_timer(Context context, final TextView Tv_sec, final TextView Tv_min, final TextView Tv_hour)
    {

        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, urlhome + "api/timer.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
             timer=new int[response.length()];
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        timer[i]=response.getInt("timer");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Gethandelmethod(Tv_sec,Tv_min,Tv_hour);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(jsonObjectRequest);
    }

    public static void Gethandelmethod(final TextView Tv_sec,final TextView Tv_min,final TextView Tv_hour)
    {
        for (int i = 0; i <timer.length ; i++) {
            if(timer[i]>zero){
                zero=timer[i];
            }
        }

       final Handler handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (zero>0){
                    try {
                       handler.post(new Runnable() {
                           @Override
                           public void run() {
                               float hour=zero/3600;
                               int horse=(int)hour;

                               float min=(zero-(horse)*3600)/60;
                               int mint=(int)min;

                               float Sec=(zero-((horse)*3600)-(mint*60));
                               int seco=(int)Sec;

                               Tv_hour.setText(String.valueOf(String.format(Locale.US,"%02d",horse)));
                               Tv_min.setText(String.valueOf(String.format(Locale.US,"%02d",mint)));
                               Tv_sec.setText(String.valueOf(String.format(Locale.US,"%02d",seco)));
                               zero--;

                           }
                       });
                       Thread.sleep(1000);
                    }catch (Exception e){

                    }
                }
            }
        }).start();


    }


}
