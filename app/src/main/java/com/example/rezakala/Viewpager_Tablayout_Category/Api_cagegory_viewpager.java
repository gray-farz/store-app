package com.example.rezakala.Viewpager_Tablayout_Category;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api_cagegory_viewpager implements Config {
    private static final String TAG = "aaa";

    public static void Getcat(final Context context, final int Index, final Getcat getcat)
    {
       final List<Datamodel_category_viewpager> dt=new ArrayList<>();
        String url=urlhome+"api/cat.php";
        Log.d(TAG, "Getcat url: "+url);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonlist=response.getJSONArray("list-cat");
                    JSONObject js=jsonlist.getJSONObject(Index);
                    try {
                       JSONArray jsonArray=js.getJSONArray("subcat");
                        for (int i = 0; i <jsonArray.length() ; i++) {
                           JSONObject jso=jsonArray.getJSONObject(i);
                           Datamodel_category_viewpager datamodel_category_viewpager=
                                   new Datamodel_category_viewpager();
                           datamodel_category_viewpager.setIdcat(jso.getString("idcat"));
                           datamodel_category_viewpager.setName(jso.getString("name"));
                           datamodel_category_viewpager.setNameen(jso.getString("nameen"));
                           datamodel_category_viewpager.setSubid(jso.getString("subid"));
                           //datamodel_category_viewpager.setIcon(jso.getString("icon"));
                           dt.add(datamodel_category_viewpager);
                        }
                        getcat.Listcat(dt);

                    }catch (Exception e){

                    }

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
        Requst_Volley.Getinstanc(context).add(jsonObjectRequest);
    }

    public interface Getcat{
        void Listcat(List<Datamodel_category_viewpager> dcv);
    }
}
