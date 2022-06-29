package com.example.rezakala.Cart.Fragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.Panel.Panel.Datamodel.Datmodel_address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api_address_frgament implements Config {
    private static final String TAG = "aaa";
   Context context;
   List<Datmodel_address> datmodel_addresses=new ArrayList<>();
    public Api_address_frgament(Context context)
    {
        this.context=context;
    }

    public void Getaddress(final in_address in_address){
        String url=urlhome+"api/list_address.php?token="+ GetToken.gettoken(context);
        //Log.d(TAG, "Getaddress: "+url);
        final JsonObjectRequest js=new JsonObjectRequest(0, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("address");
                    //Log.d(TAG, "onResponse jsonArray: ");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Datmodel_address datmodel_address=new Datmodel_address();
                        datmodel_address.setIdaddress(jsonObject.getString("idaddress"));
                        datmodel_address.setName(jsonObject.getString("name"));
                        datmodel_address.setPhonehome(jsonObject.getString("phone"));
                        datmodel_address.setMobile(jsonObject.getString("mobile"));
                        datmodel_address.setIdcity(jsonObject.getString("idcity"));
                        datmodel_address.setIdostan(jsonObject.getString("idostan"));
                        datmodel_address.setCodeposti(jsonObject.getString("codeposti"));
                        datmodel_address.setAddress(jsonObject.getString("address"));
                        datmodel_address.setIduser(jsonObject.getString("iduser"));
//                        datmodel_address.setOstan(jsonObject.getString("ostan"));
//                        datmodel_address.setCity(jsonObject.getString("city"));
                        datmodel_addresses.add(datmodel_address);
                    }
                    in_address.Address(datmodel_addresses);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "خطا در دریافت اطلاعات از سمت سرور", Toast.LENGTH_SHORT).show();
            }
        });
        js.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(js);
    }

    public interface  in_address{
        void Address(List<Datmodel_address> datmodel_addresses);
    }


}
