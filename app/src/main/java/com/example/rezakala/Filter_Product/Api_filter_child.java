package com.example.rezakala.Filter_Product;

import android.content.Context;
import android.widget.Toast;

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


public class Api_filter_child implements Config {
    private static final String TAG = "Api_filter";
    Context context;
    String idcat;
    List<Datmodel_values> datmodel_val=new ArrayList<>();
    public Api_filter_child(Context context, String idcat)
    {
        this.context=context;
        this.idcat=idcat;
    }

    public void Getinfofilter(final interface_filter interface_filter, final int possition)
    {
        String url=urlhome+"list-product.php?idcat="+idcat;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("filters");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                            if  (i==possition && possition==1){
                               datmodel_val.clear();
                            JSONArray jsarrayindex1=jsonObject.getJSONArray("values");
                            for (int j = 0; j <jsarrayindex1.length() ; j++) {
                              JSONObject jsindex1=jsarrayindex1.getJSONObject(j);
                                Datmodel_values datmodel_values=new Datmodel_values();
                                datmodel_values.setIdcolor(jsindex1.getString("idcolor"));
                                datmodel_values.setName(jsindex1.getString("name"));

                                datmodel_values.setValue(String.valueOf(jsindex1.getString("value")));
                                datmodel_val.add(datmodel_values);
                                interface_filter.getfilteritems(datmodel_val);
                            }
                        }
                           else if (i==possition && possition==2)
                            {
                                datmodel_val.clear();
                            JSONArray jsarrayindex1=jsonObject.getJSONArray("values");
                            for (int j = 0; j <jsarrayindex1.length() ; j++) {
                                JSONObject jsindex1=jsarrayindex1.getJSONObject(j);
                                Datmodel_values datmodel_values=new Datmodel_values();
                                datmodel_values.setIdbrand(jsindex1.getString("idbrand"));
                                datmodel_values.setName(jsindex1.getString("name"));
                                datmodel_values.setNameen(jsindex1.getString("nameen"));
                                datmodel_val.add(datmodel_values);
                                interface_filter.getfilteritems(datmodel_val);
                            }
                        }

                           else if(i==possition && possition==3) {
                              datmodel_val.clear();
                               JSONObject jsindex0 = jsonObject.getJSONObject("values");
                               Datmodel_values datmodel_values=new Datmodel_values();

                               datmodel_values.setName(jsindex0.getString("دوسیم کارته"));

                               datmodel_val.add(datmodel_values);
                               interface_filter.getfilteritems(datmodel_val);
                           }

                           else if(i==possition && possition==4) {
                              datmodel_val.clear();
                               JSONObject jsindex0 = jsonObject.getJSONObject("values");
                               Datmodel_values datmodel_values=new Datmodel_values();
                               datmodel_values.setName(jsindex0.getString("2"));
                               datmodel_val.add(datmodel_values);
                               interface_filter.getfilteritems(datmodel_val);
                           }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "خطا در برقرار ارتباط", Toast.LENGTH_SHORT).show();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(jsonObjectRequest);
    }

    public interface  interface_filter{
        void getfilteritems(List<Datmodel_values> datmodel_values);
    }
}
