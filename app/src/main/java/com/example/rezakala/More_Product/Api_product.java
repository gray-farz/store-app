package com.example.rezakala.More_Product;

import android.content.Context;
import android.content.SharedPreferences;
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

public class Api_product implements Config {
    private static final String TAG = "aaa";
    public static void Getpost(final Context context,
                               int idproduct,
                               final interface_poduct interface_poduct,
                               final interface_images interface_images)
    {

        String url;
        SharedPreferences sharedPreferences=
                context.getSharedPreferences("info",0);
        final String toekn=
                sharedPreferences.getString("token",null);
        //Log.d(TAG, "Getpost token: "+toekn+" idproduct "+idproduct);
        if (toekn==null){
            url=urlhome + "api/product.php?idproduct="+idproduct;
        }
        else
        {
            url=urlhome + "api/product.php?idproduct="+idproduct+"&token="+toekn;

        }
        //Log.d(TAG, "Getpost:url "+url);
      final   List<Datamodel_product> datamodel_products=new ArrayList<>();
      final   List<String> listImages=new ArrayList<>();

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0,
                url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse: in Api_product");
                try {
                    JSONObject jsonObjectinfo=response.getJSONObject("info");
                    Datamodel_product datamodel_product=new Datamodel_product();
                    if(toekn!=null){
                        JSONObject jsonObject=response.getJSONObject("foruser");
                        datamodel_product.setCheck_fav(jsonObject.getString("fav"));
                    }
                    datamodel_product.setIdproduct(jsonObjectinfo.getString("idproduct"));
                    datamodel_product.setName(jsonObjectinfo.getString("name"));
                    //Log.d(TAG, "name in Getpost: "+jsonObjectinfo.getString("name"));
                    datamodel_product.setNameen(jsonObjectinfo.getString("nameen"));
                    datamodel_product.setIdbrand(jsonObjectinfo.getString("idbrand"));
                    datamodel_product.setColors(jsonObjectinfo.getString("colors"));
                    datamodel_product.setStatus(jsonObjectinfo.getString("status"));
                    datamodel_product.setDes(jsonObjectinfo.getString("description"));
                    //datamodel_product.setKholase(jsonObjectinfo.getString("kholase"));
                    datamodel_product.setPic(jsonObjectinfo.getString("pic"));
                    //datamodel_product.setPic(jsonObjectinfo.getString("pic"));
                    datamodel_product.setIdcat(jsonObjectinfo.getString("idcat"));
                    datamodel_product.setWeight(jsonObjectinfo.getString("weight"));


                    JSONArray jsonArrayimages=response.getJSONArray("images");
                    for (int i = 0; i <jsonArrayimages.length() ; i++) {
                        JSONObject jsonObject=jsonArrayimages.getJSONObject(i);
                        listImages.add(jsonObject.getString("url"));
                        //Log.d(TAG, "onResponse: "+jsonObject.getString("url"));
                    }

                    JSONArray jsonarray_prices=response.getJSONArray("price");
                    if(jsonarray_prices.length()>0) {
                        datamodel_product.setCheck_service(true);
                        datamodel_product.setLen(String.valueOf(jsonarray_prices.length()));
                        JSONObject jsonObject = jsonarray_prices.getJSONObject(0);
                        datamodel_product.setPrice(jsonObject.getString("price_sale"));
                        datamodel_product.setIdstore(jsonObject.getString("idstore"));
                        datamodel_product.setG(jsonObject.getString("garanti"));
                        datamodel_product.setIdcolor(jsonObject.getString("idcolor"));
                    }
                    else
                    {
                        datamodel_product.setCheck_service(false);
                    }
                    //Log.d(TAG, "onResponse: end");
                    datamodel_products.add(datamodel_product);
                    interface_poduct.list(datamodel_products);
                    interface_images.listimages(listImages);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "JSONException: "+e.toString());
                }

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

    public interface interface_poduct {
        void list(List<Datamodel_product> datamodel_products);
    }

    public interface interface_images {
        void listimages(List<String> Images);
    }

}
