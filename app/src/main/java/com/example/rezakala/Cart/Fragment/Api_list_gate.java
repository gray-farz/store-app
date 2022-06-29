package com.example.rezakala.Cart.Fragment;

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

public class Api_list_gate implements Config {
    private static final String TAG = "aaa";
    public static void Get_list_gate(Context context, final Get_gate get_gate)
    {
        final List<Datamodel_gate> datamodel_gates=new ArrayList<>();
        String url=urlhome+"api/list_gate.php";
        //Log.d(TAG, "Get_list_gate: "+url);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("gates");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                         JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Datamodel_gate datamodel_gate=new Datamodel_gate();
                        datamodel_gate.setName(jsonObject.getString("name"));
                        datamodel_gate.setIcon(jsonObject.getString("icon"));
                        datamodel_gate.setUrl(jsonObject.getString("url"));
                        datamodel_gates.add(datamodel_gate);
                    }
                    get_gate.List_gete(datamodel_gates);
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

    public interface  Get_gate
    {
        void List_gete(List<Datamodel_gate> list);
    }
}
