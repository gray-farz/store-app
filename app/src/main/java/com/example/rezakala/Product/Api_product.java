package com.example.rezakala.Product;

import android.content.Context;
import android.util.Log;

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

public class Api_product implements Config 
{
    private static final String TAG = "aaa";
   public static void Getlist_product(final String arrayname,
                                      Context context,
                                      final Listproduct listproduct)
   {
       final List<Datamodel_listproduct> datamodel_listproducts=new ArrayList<>();
       String url=urlhome+"home.php";
       JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               try {
                   //Log.d(TAG, "Getlist_product : 0 "+arrayname);
                   JSONArray jsonArray=response.getJSONArray(arrayname);
                   //Log.d(TAG, "Getlist_product: length "+jsonArray.length());
                   for (int i = 0; i <jsonArray.length() ; i++) {
                      JSONObject jsonObject=jsonArray.getJSONObject(i);
                      Datamodel_listproduct datamodel_listproduct=new Datamodel_listproduct();
                      datamodel_listproduct.setIdstore(jsonObject.getString("idstore"));
                      datamodel_listproduct.setIdproduct(jsonObject.getString("idproduct"));
                      datamodel_listproduct.setPrice_sale(jsonObject.getString("price_sale"));
                      datamodel_listproduct.setName(jsonObject.getString("name"));
                      datamodel_listproduct.setPic(jsonObject.getString("pic"));
                      datamodel_listproduct.setGaranti(jsonObject.getString("garanti"));
                       datamodel_listproducts.add(datamodel_listproduct);
                       //Log.d(TAG, "Getlist_product: i value "+jsonObject.getString("pic"));
                   }
                   //Log.d(TAG, "after loop");
                   listproduct.Listpost(datamodel_listproducts);
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

    public interface Listproduct
    {
        void Listpost(List<Datamodel_listproduct> datamodel_listproducts);
    }
}
