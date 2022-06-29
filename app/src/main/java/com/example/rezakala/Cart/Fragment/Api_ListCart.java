package com.example.rezakala.Cart.Fragment;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Cart.Datamodel_Cart;
import com.example.rezakala.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api_ListCart implements Config {
    private static final String TAG = "aaa";
    public static void Get_list_Cart(Context context, final Get_List_Cart get_list_cart)
    {
        String url=urlhome+"api/list_cart.php?token="+ GetToken.gettoken(context);
        Log.d(TAG, "Get_list_Cart url: "+url);
        final  List<Datamodel_Cart> list=new ArrayList<>();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse in Get_list_Cart: "+response.toString() );
                try {
                    JSONArray jsonArray=response.getJSONArray("list-product");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        //Log.d(TAG, "onResponse i first: "+i);
                       JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Datamodel_Cart datamodel_cart=new Datamodel_Cart();
                        datamodel_cart.setIdcart(jsonObject.getString("idcart"));
                        datamodel_cart.setIdstore(jsonObject.getString("idstore"));
                        datamodel_cart.setNumber(jsonObject.getString("count"));
                        datamodel_cart.setPrice(jsonObject.getString("price"));
                        datamodel_cart.setTotal_price(jsonObject.getString("pricetotal"));
                        datamodel_cart.setIdcolor(jsonObject.getString("idcolor"));
                        datamodel_cart.setService(jsonObject.getString("garanti"));
                        datamodel_cart.setIdproduct(jsonObject.getString("idproduct"));

                        datamodel_cart.setNameproduct(jsonObject.getString("nameproduct"));
                        datamodel_cart.setImage(jsonObject.getString("img"));
                        datamodel_cart.setColor(jsonObject.getString("namecolor"));
                        list.add(datamodel_cart);
                        //Log.d(TAG, "onResponse i end");
                    }
                    get_list_cart.Get_list(list);
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
    public interface Get_List_Cart{
        void Get_list(List<Datamodel_Cart> list);
    }
}
