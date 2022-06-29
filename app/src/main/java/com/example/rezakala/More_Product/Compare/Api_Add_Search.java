package com.example.rezakala.More_Product.Compare;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.Product.Datamodel_listproduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Api_Add_Search implements Config {
    private static final String TAG = "aaa";

    public static void Get_search(Context context, String name, final Get_info get_info)
    {
        final List<Datamodel_listproduct> listproducts=new ArrayList<>();
        String url=urlhome+"api/list_product.php?idcat=4&name="+name;
        Log.d(TAG, "Get_search url: "+url);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("products");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Datamodel_listproduct datamodel_listproduct=new Datamodel_listproduct();
                        datamodel_listproduct.setIdproduct(jsonObject.getString("idproduct"));
                        datamodel_listproduct.setName(jsonObject.getString("name"));
                        datamodel_listproduct.setNameen(jsonObject.getString("nameen"));
                        datamodel_listproduct.setPic(jsonObject.getString("img"));
                        listproducts.add(datamodel_listproduct);
                    }
                    get_info.Listinfo(listproducts);
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

    public interface Get_info
    {
        void Listinfo(List<Datamodel_listproduct> list);
    }
}
