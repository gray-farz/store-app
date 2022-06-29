package com.example.rezakala.Cart.Fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Cart.Datamodel_Cart;
import com.example.rezakala.Cart.Db_DataBase;
import com.example.rezakala.Config;
import com.example.rezakala.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_send_check extends Fragment implements Config {
    private static final String TAG = "aaa";
    RecyclerView recyclerview_cart,recyclerview_gate;
    ImageView Send_save;
    TextView Tv_user_send;
    Adapter_list_gate adapter_list_gate;
    String name;
    String address;
    String phone;
    SwitchCompat sw_exits;
    RelativeLayout Rel_expand_offer;
    TextView Tv_save_t;
    String Idaddress;
    EditText ET_offer;
    String codeorder="null";
    int intent_pardakht=-1;
    Db_DataBase db_dataBase;
    public Fragment_send_check() {
        // Required empty public constructor
    }

    public void Get_Send_user(String Idaddress,String name,String address,String phone){
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.Idaddress=Idaddress;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.layout_order_send_check, container, false);
        db_dataBase=new Db_DataBase(getActivity());
        sw_exits=view.findViewById(R.id.sw_exits);
        Rel_expand_offer=view.findViewById(R.id.Rel_expand_offer);
        Tv_save_t=view.findViewById(R.id.Tv_save_t);
        ET_offer=view.findViewById(R.id.ET_offer);

//        Tv_save_t.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!ET_offer.getText().toString().trim().isEmpty()){
//                    Get_price(getActivity(),view,Idaddress,true);
//                }
//
//            }
//        });
//
//        sw_exits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    Rel_expand_offer.setVisibility(View.VISIBLE);
//                }else
//                {
//                    Rel_expand_offer.setVisibility(View.GONE);
//                }
//            }
//        });
        Api_list_gate.Get_list_gate(getActivity(),
                new Api_list_gate.Get_gate() {
            @Override
            public void List_gete(List<Datamodel_gate> list) {
                recyclerview_gate=view.findViewById(R.id.recyclerview_gate);
                recyclerview_gate.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter_list_gate=new Adapter_list_gate(getActivity(), list, new Adapter_list_gate.Get_possion() {
                    @Override
                    public void Get_p(int p) {
                        intent_pardakht=p;
                    }
                });
                recyclerview_gate.setAdapter(adapter_list_gate);
            }
        });

        Api_ListCart.Get_list_Cart(getActivity(),
                new Api_ListCart.Get_List_Cart() {
            @Override
            public void Get_list(List<Datamodel_Cart> list) {
                //Log.d(TAG, "Get_list in Get_list_Cart: "+list.size());
                recyclerview_cart=view.findViewById(R.id.recyclerview_cart);
                recyclerview_cart.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                Adapter_List_Cart adapter_list_cart=new Adapter_List_Cart(getActivity(),list);
                recyclerview_cart.setAdapter(adapter_list_cart);
            }
        });

        String t="تمامی مرسوله های ارسالی به "+name+" "+"به آدرس "+address+" "+"و به شماره "+phone +" "+"تحویل داده خواهد شد.";
        Tv_user_send=view.findViewById(R.id.Tv_user_send);
        Tv_user_send.setText(t);
        Send_save=view.findViewById(R.id.Send_save);
        Send_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(codeorder=="null"){
                  Log.d(TAG, "onClick: codeorder==null");
                  Toast.makeText(getActivity(), "خطا در ارسال به سمت پرداخت اینترنتی", Toast.LENGTH_SHORT).show();
              }else
              {
                  Log.d(TAG, "onClick: codeorder not null");
                  if(intent_pardakht!=-1)
                  {
                      if(intent_pardakht==0) {

                          String url = urlhome + "api/gate/zarinpal.php?codeorder=" + codeorder;
                          Log.d(TAG, "onClick intent_pardakht==0 url: "+url);
                          Intent intent = new Intent(Intent.ACTION_VIEW);
                          intent.setData(Uri.parse(url));
                          getActivity().finish();
                          db_dataBase.Delete_product();

                          try {
                              startActivity(intent);
                          } catch (Exception e) {

                          }
                      }
                      else{

                      }

                  }
                  else
                  {
                      Toast.makeText(getActivity(), "خطا در انتخاب درگاه", Toast.LENGTH_SHORT).show();

                  }


              }

            }
        });
        Get_price(getActivity(),view,Idaddress,false);
        return view;
    }


    void Get_price(Context context,View view,String Id_address,boolean check)
    {
        String url;
       final TextView Tv_price=view.findViewById(R.id.Tv_price);
       final TextView Tv_price_post=view.findViewById(R.id.Tv_price_post);
        if(check){
            url=urlhome+"api/setorder.php?token="+ GetToken.gettoken(context)+"&idaddress="+Id_address+"&codetakhfif="+ET_offer.getText().toString();
        }
        else{
         url=urlhome+"api/setorder.php?token="+ GetToken.gettoken(context)+"&idaddress="+Id_address;
          }
        //Log.d(TAG, "Get_price: "+url);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Log.d(TAG, "onResponse Get_price: " );
                    Tv_price.setText(response.getString("pricetotal"));
                    codeorder=response.getString("codeorder");
                    String check=response.getString("postpishtaz");
                    if(check.equals("0")){
                        //Log.d(TAG, "free post ");
                        Tv_price_post.setText("رایگان");
                    }else {
                        //Log.d(TAG, "post pay ");
                        Tv_price_post.setText(check);
                    }

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

}
