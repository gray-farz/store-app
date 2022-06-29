package com.example.rezakala.Viewpager_Tablayout_Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Viewpager_Category extends AppCompatActivity implements Config {
    ImageView Im_back;
    TabLayout tablayout;
    ViewPager viewpager;
    Adapter_viewpager_category adapter_viewpager_category;
    private static final String TAG = "aaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_category);

        Cast();
        Setup_Viewpager();
    }

    void Setup_Viewpager()
    {
        String url=urlhome+"api/cat.php";
        Log.d(TAG, "Setup_Viewpager url: "+url);
        final List<String> listtitle =new ArrayList<>();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonarraylist=response.getJSONArray("list-cat");
                    for (int i = 0; i <jsonarraylist.length() ; i++) {
                        JSONObject js=jsonarraylist.getJSONObject(i);
                        listtitle.add(js.getString("name"));
                    }
                    adapter_viewpager_category=new
                            Adapter_viewpager_category(Viewpager_Category.this
                            ,listtitle);
                    viewpager.setAdapter(adapter_viewpager_category);
                    tablayout.setupWithViewPager(viewpager);

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
        Requst_Volley.Getinstanc(getApplicationContext()).add(jsonObjectRequest);
    }
    void Cast()
    {
        Im_back=findViewById(R.id.Im_back);
        tablayout=findViewById(R.id.tablayout);
        viewpager=findViewById(R.id.viewpager);
        Im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}