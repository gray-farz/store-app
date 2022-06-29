package com.example.rezakala.Panel.Api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.MainActivity;
import com.example.rezakala.Panel.Panel.Main_profile_panel;
import com.example.rezakala.Panel.Verfy_Code;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Api_panel implements Config {
    private static final String TAG = "aaa";
    Context context;
    ProgressWheel progressWheel;

    public Api_panel(Context context, ProgressWheel progressWheel) {
        this.context = context;
        this.progressWheel = progressWheel;
    }


    public void GetRegister_panel(final String phone, final String pass){
        //Log.d(TAG, "GetRegister_panel: ");
        progressWheel.setVisibility(View.VISIBLE);
        String url=urlhome+"api/reg.php"+"?username="+phone.trim()+"&password="+pass;
        Log.d(TAG, "GetRegister_panel: 1 "+url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressWheel.setVisibility(View.GONE);
                try
                {
                    String check=response.getString("status");
                    Log.d(TAG, "onResponse: check "+check);
                    if(check.equals("ok"))
                    {
                        Log.d(TAG, "onResponse: ok");
                        Intent intent=new Intent(context, Verfy_Code.class);
                        intent.putExtra("phone",phone);
                        intent.putExtra("reg","reg");
                        context.startActivity(intent);
                    }else
                    {
                        //Toast.makeText(context, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: check not ok");
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d(TAG, "JSONException e: "+e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressWheel.setVisibility(View.GONE);
                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("username",phone);
                hashMap.put("password",pass);
                return hashMap;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(jsonObjectRequest);

    }


//    public void Get_send_code_reply(final String phone){
//        progressWheel.setVisibility(View.VISIBLE);
//        String url=urlhome+"forget.php";
//        StringRequest stringRequest=new StringRequest(1, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "onResponse: "+response );
//                progressWheel.setVisibility(View.GONE);
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    String check=jsonObject.getString("status");
//                    if(check.equals("ok"))
//                    {
//                        Intent intent = new Intent(context, Verfy_Code.class);
//                        intent.putExtra("phone", phone);
//                        intent.putExtra("reg", "change");
//                        context.startActivity(intent);
//                    }
//                    else
//                    {
//                        Toast.makeText(context, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressWheel.setVisibility(View.GONE);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> hashMap=new HashMap<>();
//                hashMap.put("username",phone);
//                return hashMap;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Requst_Volley.Getinstanc(context).add(stringRequest);
//    }
//
//
//
//
//    public void Get_changepass(final String phone, final String pass){
//        progressWheel.setVisibility(View.VISIBLE);
//        String url=urlhome+"changepass.php";
//        StringRequest stringRequest=new StringRequest(1, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                progressWheel.setVisibility(View.GONE);
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    String check=jsonObject.getString("status");
//                    if(check.equals("ok"))
//                    {
//                        Intent intent=new Intent(context,Main_profile_panel.class);
//                        context.startActivity(intent);
//                        ((Activity)context).finish();
//                    }
//                    else
//                    {
//                        Toast.makeText(context, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressWheel.setVisibility(View.GONE);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> hashMap=new HashMap<>();
//                hashMap.put("token",GetToken.gettoken(context).toString());
//                hashMap.put("oldpass",phone);
//                hashMap.put("newpass",pass);
//                return hashMap;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Requst_Volley.Getinstanc(context).add(stringRequest);
//    }
//
//
    public void Get_login(final String phone, final String code){
        Log.d(TAG, "Get_login: ");
        progressWheel.setVisibility(View.VISIBLE);
        String url=urlhome+"api/login.php"+"?username="+phone+"&password="+code;
        Log.d(TAG, "url: "+url);
        StringRequest stringRequest=new StringRequest(1, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse Get_login: ");
                progressWheel.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String check=jsonObject.getString("status");
                    if(check.equals("ok"))
                    {
                        SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("token",jsonObject.getString("token"));
                        editor.apply();
                        Intent intent=new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                    else
                    {
                        Toast.makeText(context, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressWheel.setVisibility(View.GONE);
                Log.d(TAG, "onErrorResponse: Get_login "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("username",phone);
                hashMap.put("password",code);
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(stringRequest);
    }
    public void GetSendcode_Panel(final String phone, final String code, final String reg){
        String url;
        progressWheel.setVisibility(View.VISIBLE);
        if(reg.equals("reg")) {
           url = urlhome + "api/chkcode.php"+"?username="+phone.trim()+"&code="+code.trim();
        }else
        {
            url = urlhome + "api/chkcodeforget.php"+"?username="+phone.trim()+"&code="+code.trim();
        }
        Log.d(TAG, "GetSendcode_Panel: "+url);
        StringRequest stringRequest=new StringRequest(1, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressWheel.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String check=jsonObject.getString("status");

                    if(check.equals("ok"))
                    {
                        SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("token",jsonObject.getString("token"));
                        editor.apply();
                        Log.d(TAG, "onResponse: token ");

                        if(reg.equals("reg")) {
                            Intent intent = new Intent(context, Main_profile_panel.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
//                        else
//                        {
//                            Intent intent = new Intent(context, Changepassword.class);
//                            context.startActivity(intent);
//                            ((Activity) context).finish();
//                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressWheel.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("username",phone);
                hashMap.put("code",code);
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(stringRequest);
    }


}
