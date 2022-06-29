package com.example.rezakala.Panel.Panel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Getinfo;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Main_profile_panel extends AppCompatActivity {
    RecyclerView recyclerview;
    Adapter_panel adapter_panel;
    TextView Tv_phone,Tv_name;
    ImageView Im_back;
    private static final String TAG = "aaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile_panel2);

        Getinfo.Getexit(Main_profile_panel.this);

        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter_panel=new Adapter_panel(this,Api_feck_panel.Panelapifeck(this));
        recyclerview.setAdapter(adapter_panel);
        Cast();
        Getinfo();
        Im_back=findViewById(R.id.Im_back);
        Im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void Cast()
    {
        Tv_name=findViewById(R.id.Tv_name);
        Tv_phone=findViewById(R.id.Tv_phone);

    }

    void Getinfo()
    {
        String url= Config.urlhome+"api/userinfo.php?token="+ GetToken.gettoken(getApplicationContext());
        Log.d(TAG, "Getinfo: url "+url);
        JsonObjectRequest js=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse:in Main_profile ");
                try {
                    JSONObject jsonObject=response.getJSONObject("userinfo");
                    Tv_name.setText(jsonObject.getString("name"));
                    Tv_phone.setText(jsonObject.getString("mobile"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main_profile_panel.this, "خطا در دریافت اطلاعات از سمت سرور", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse in Main_profile: ");
            }
        });

        js.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(getApplicationContext()).add(js);
    }
}