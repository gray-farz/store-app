package com.example.rezakala.Product_offer;

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

public class Api_product_offer implements Config 
{
    private static final String TAG = "aaa";

   public static void Getlist_product(final String arrayname,Context context,final Listproduct listproduct)
   {
       final List<Datamodel_listproduct> datamodel_listproducts=new ArrayList<>();
       String url=urlhome+"home.php";
       JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response)
           {
               //Log.d(TAG, "onResponse befor try: ");
               try {
                   JSONArray jsonArray=response.getJSONArray(arrayname);
                   //Log.d(TAG, "jsonArray.length(): "+jsonArray.length());
                   for (int i = 0; i <jsonArray.length() ; i++) {
                      JSONObject jsonObject=jsonArray.getJSONObject(i);
                      Datamodel_listproduct datamodel_listproduct=new Datamodel_listproduct();
                      datamodel_listproduct.setIdstore(jsonObject.getString("idstore"));
                      datamodel_listproduct.setIdproduct(jsonObject.getString("idproduct"));
                      datamodel_listproduct.setPrice_sale(jsonObject.getString("price_sale"));
                       datamodel_listproduct.setOffer(jsonObject.getString("price_offer"));
                      datamodel_listproduct.setName(jsonObject.getString("name"));
                      datamodel_listproduct.setPic(jsonObject.getString("pic"));

                       //Log.d(TAG, "onResponse in loop: "+jsonObject.getString("pic"));
                       datamodel_listproducts.add(datamodel_listproduct);
                   }
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

       jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(milisec,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       Requst_Volley.Getinstanc(context).add(jsonObjectRequest);
   }





    public interface Listproduct
    {
        void Listpost(List<Datamodel_listproduct> datamodel_listproducts);
    }
}
