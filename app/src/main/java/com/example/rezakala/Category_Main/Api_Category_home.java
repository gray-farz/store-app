package com.example.rezakala.Category_Main;

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

public class Api_Category_home implements Config {

    public static void Category(Context context, final Get_category get_category)
    {
       final List<Datamodel_cagegory_home> datamodel_cagegory_homes=new ArrayList<>();
      String url=urlhome+"api/cat.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("list-cat");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonobject_category=jsonArray.getJSONObject(i);
                        Datamodel_cagegory_home datamodel_cagegory_home=new Datamodel_cagegory_home();
                        datamodel_cagegory_home.setIdcat(jsonobject_category.getString("idcat"));
                        datamodel_cagegory_home.setTitle(jsonobject_category.getString("name"));
                        datamodel_cagegory_home.setTitleng(jsonobject_category.getString("nameen"));
                        datamodel_cagegory_homes.add(datamodel_cagegory_home);
                    }
                    get_category.getcategory(datamodel_cagegory_homes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                get_category.Error("خطا در دریافت اطلاعات از سمت سرور");
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(jsonObjectRequest);
    }


    public interface Get_category{
        void getcategory(List<Datamodel_cagegory_home> list);
        void Error(String s);
    }
}
