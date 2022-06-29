package com.example.rezakala.Pack_sand_count;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rezakala.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api_sandcount implements Config {
     Context context;
     public Api_sandcount(Context context)
     {
         this.context=context;
     }
    public void Setup_Sapndcountmethod(final Getspandcount getspandcount) {
        final List<Datamodel_sandcount> stringsarray=new ArrayList<>();
        String url=urlhome+"home.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("slider");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Datamodel_sandcount datamodel_sandcount=new Datamodel_sandcount();
                        datamodel_sandcount.setId(jsonObject.getString("idslider"));
                        datamodel_sandcount.setPic(jsonObject.getString("pic"));
                        stringsarray.add(datamodel_sandcount);
                    }
                    getspandcount.Listsandcount(stringsarray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public interface Getspandcount{
         void Listsandcount(List<Datamodel_sandcount> datamodel_sandcounts);
    }



}
