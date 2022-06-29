package com.example.rezakala.Viewpager_Tablayout_Category.More_Category;

import android.content.Context;

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

public class Api_Expendable_recyclerview implements Config {
    private static final String TAG = "Api_Expendable_recycler";
    Context context;
    List<PeopleListItem> items=new ArrayList<>();
    public Api_Expendable_recyclerview(Context context)
    {
        this.context=context;
    }

    public void Getlistexpandablerecyervliew(final getlist getlist)
    {
        String url=urlhome+"cat.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("list-cat");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        items.add(new PeopleListItem(jsonObject.getString("name"),jsonObject.getString("idcat")));
                        try {
                            JSONArray array=jsonObject.getJSONArray("subcat");
                            for (int j = 0; j <array.length() ; j++) {
                                JSONObject jsonObject1=array.getJSONObject(j);
                                items.add(new PeopleListItem(jsonObject1.getString("name"),jsonObject1.getString("idcat"),""));
                            }

                        }catch (Exception e){

                        }
                    }
                    getlist.listexpand(items);

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

    public interface getlist{
        void listexpand(List<PeopleListItem> expanditems);
    }
}
