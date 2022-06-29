package com.example.rezakala.Filter_Product;

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

public class Api_filter implements Config {
    private static final String TAG = "Api_filter";
    Context context;
    String idcat;
    List<Datmodel_values> datmodel_val=new ArrayList<>();
    List<Datamodel_filter> datamodel_filters=new ArrayList<>();
    public Api_filter(Context context, String idcat)
    {
        this.context=context;
        this.idcat=idcat;
    }

    public void Getinfofilter(final interface_filter interface_filter)
    {
        String url=urlhome+"list-product.php?idcat="+idcat;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("filters");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Datamodel_filter filter=new Datamodel_filter();
                        filter.setNameen(jsonObject.getString("nameen"));
                        filter.setNamefa(jsonObject.getString("namefa"));
                        datamodel_filters.add(filter);
                    }
                    interface_filter.listfilter(datamodel_filters);

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

    public interface  interface_filter{
        void listfilter(List<Datamodel_filter> datamodel_filters);
    }
}
