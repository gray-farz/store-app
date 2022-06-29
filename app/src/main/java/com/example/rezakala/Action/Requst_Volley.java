package com.example.rezakala.Action;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class Requst_Volley {

    static RequestQueue requestQueuegetinstanc;
    public static RequestQueue Getinstanc(Context context)
    {
        if(requestQueuegetinstanc==null){
            requestQueuegetinstanc= Volley.newRequestQueue(context);
        }
        return requestQueuegetinstanc;
    }
}
