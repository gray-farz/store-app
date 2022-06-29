package com.example.rezakala.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Cart.Fragment.Send_Order_Activity;
import com.example.rezakala.Config;
import com.example.rezakala.Panel.Login_Activity;
import com.example.rezakala.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cart_activity extends AppCompatActivity implements Config {
    ImageView Im_close;
    TextView Tv_price_final;
    RecyclerView recyclerview_cart;
    ImageView Im_send;
    Db_DataBase db_dataBase;
    Adapter_Cart adapter;
    RelativeLayout Rel_error,Rel_sum;
    TextView Tv_null;
    Button Btn_show_action;
    boolean check_login=false;
    Cursor cursor;
    private static final String TAG = "aaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Cast();
        Get_price();
        Get_Setup_Recyclerview();
    }

    void Cast()
    {
        db_dataBase=new Db_DataBase(this);
        Im_close=findViewById(R.id.Im_close);
        Tv_price_final=findViewById(R.id.Tv_price_final);
        recyclerview_cart=findViewById(R.id.recyclerview_cart);
        Im_send=findViewById(R.id.Im_send);
        Rel_error=findViewById(R.id.Rel_error);
        Rel_sum=findViewById(R.id.Rel_sum);
        Tv_null=findViewById(R.id.Tv_null);
        Btn_show_action=findViewById(R.id.Btn_show_action);

        Im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Im_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("info", 0);
                String toekn = sharedPreferences.getString("token", null);
                if (toekn == null) {
                    Intent intent = new Intent(getApplicationContext(),
                            Login_Activity.class);
                    startActivity(intent);
                }
                else{

                    long check_record = db_dataBase.Get_count_record();
                    //Log.d(TAG, "onClick Im_send: "+check_record);
                    if (check_record == 0) {
                        Intent intent = new Intent(getApplicationContext(),
                                Send_Order_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Cursor cursor = db_dataBase.Get_Cursor();
                        Log.d(TAG, "onClick: cursor.getCount() "
                                +cursor.getCount());
                        if (cursor.getCount() > 0) {
                            while (cursor.moveToNext())
                            {
                                _Send_Idstore(cursor.getString(1), cursor.getString(9));
                            }
                            cursor.close();

                        } else {
                            return;
                        }

                    }

                }

            }
        });

    }
    void _Send_Idstore(final String idstore, String count)
    {
        String url = urlhome + "api/addcart.php?token=" + GetToken.gettoken(getApplicationContext()) + "&idstore=" + idstore + "&count=" + count;
        Log.d(TAG, "_Send_Idstore url : "+url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( 0, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse in _Send_Idstore: ");
                try {
                    String check=response.getString("status");
                    if(check.equals("ok"))
                    {
                        //Log.d(TAG, "onResponse : check.equals");
                        db_dataBase.Get_remove_idstore(idstore);
                        long check_record=db_dataBase.Get_count_record();
                        if(check_record==0){
                            //Log.d(TAG, "onResponse : check_record==0");
                            Intent intent=new Intent(getApplicationContext(), Send_Order_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse in _Send_Idstore: "+error.toString());
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(getApplicationContext()).add(jsonObjectRequest);

    }

    void Get_price()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("info",0);
        String toekn=sharedPreferences.getString("token",null);
        if (toekn==null) {
            check_login=false;
        }else
        {
            check_login=true;
        }

        long t=db_dataBase.Final_price_product();
        if(t!=0) {
            Tv_price_final.setText(String.valueOf(db_dataBase.Final_price_product()));
            Rel_error.setVisibility(View.GONE);
            Rel_sum.setVisibility(View.VISIBLE);
        }
        else
        {
            if(check_login){
                Tv_null.setText("سبد خرید شما خالی است !");
                Im_send.setVisibility(View.GONE);
                Btn_show_action.setText("نمایش خرید های قبلی");
            }else
            {
                Im_send.setVisibility(View.GONE);
                Tv_null.setText("سبد خرید شما خالی است !");
                Btn_show_action.setText("ورود به حساب کاربری");
            }
            Rel_error.setVisibility(View.VISIBLE);
            Rel_sum.setVisibility(View.GONE);
        }

    }
    void Get_Setup_Recyclerview()
    {
        List<Integer> list=new ArrayList<>();
        for (int i = 1; i <11 ; i++) {
            list.add(i);
        }

        recyclerview_cart.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter_Cart(db_dataBase,
                this, db_dataBase.Get_info(), list,
                new Adapter_Cart.Get_Change_proce_final() {
            @Override
            public void Strchange_price() {
                Get_price();
            }
        });
        recyclerview_cart.setAdapter(adapter);
    }

}