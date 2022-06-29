package com.example.rezakala.Packslider;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rezakala.Action.Convert_px;
import com.example.rezakala.Config;
import com.example.rezakala.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Slider_config implements Config {
    Context context;
    ProgressWheel progress_wheel;
    ViewPager viewPager;
    Activity activity;
    int idview[];
    int countslider;
    LinearLayout linerlayout_slider;
    Slider_pageradapter slider_pageradapter;
    private static final String TAG = "aaa";
    public Slider_config(Activity activity,
                         Context context, ProgressWheel progress_wheel,
                         ViewPager viewPager, LinearLayout linerlayout_slider)
    {
        this.context=context;
        this.progress_wheel=progress_wheel;
        this.viewPager=viewPager;
        this.activity=activity;
        this.linerlayout_slider=linerlayout_slider;
        Setup_slider();
    }

    void Setup_slider()
    {
        final List<String> stringsarray=new ArrayList<>();
        String url=urlhome+"home.php";
        //String url=urlhome+"digikala/home.php";

        StringRequest stringRequest=new StringRequest(0, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progress_wheel.setVisibility(View.GONE);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("slider");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        stringsarray.add(jsonObject1.getString("pic"));
                    }
                    idview=new int[jsonArray.length()];
                    slider_pageradapter=new Slider_pageradapter(activity,context,
                            stringsarray,idview);
                    viewPager.setAdapter(slider_pageradapter);
                    Getslider_auto_Changeitems(jsonArray.length());
                    Sliderindactor(jsonArray.length());
                } catch (JSONException e) {
                    Log.d(TAG, "catch: "+e.toString());
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress_wheel.setVisibility(View.GONE);
                        Toast.makeText(context, "خطا در دریافت اطلاعات از سمت سرور", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onErrorResponse: "+error.toString());
                    }
                }
        );
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(stringRequest);

    }

    void Sliderindactor(final int len)
    {

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(Convert_px.convertpx(30,context),Convert_px.convertpx(30,context));
        layoutParams.setMargins(0,0,13,0);
        for (int i = 0; i <len ; i++) {
            int id=View.generateViewId();
            idview[i]=id;
            View view=new View(activity);
            view.setBackgroundResource(R.drawable.shape_slider_noactive);
            view.setLayoutParams(layoutParams);
            view.setId(id);
            linerlayout_slider.addView(view);
        }

    }

    void Getslider_auto_Changeitems(final int len)
    {
        final Handler handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                countslider=0;
                while (true){
                    try {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(countslider);
                                for (int i = 0; i <idview.length ; i++) {
                                    View view=activity.findViewById(idview[i]);
                                    if(i==countslider){
                                        view.setBackgroundResource(R.drawable.shape_slider_active);
                                    }
                                    else
                                    {
                                        view.setBackgroundResource(R.drawable.shape_slider_noactive);
                                    }
                                }
                                countslider++;
                            }
                        });
                        Thread.sleep(5000);
                        if(countslider==len){
                            countslider=0;
                        }
                    }catch (Exception e){

                    }
                }
            }
        }).start();
    }

}
