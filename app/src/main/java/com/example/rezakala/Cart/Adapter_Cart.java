package com.example.rezakala.Cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.More_Product.More_Product;
import com.example.rezakala.R;

import org.json.JSONObject;

import java.util.List;

public class Adapter_Cart extends RecyclerView.Adapter<Adapter_Cart.videwholder> implements Config {
    private static final String TAG = "Adapter_Cart";
    Context context;
    List<Datamodel_Cart> Data;
    Get_Change_proce_final get_change_proce_final;
    List<Integer> listnumber;
    Db_DataBase db_dataBase;
    int discount=0;
    int offer=1;
    int count=0;
    public Adapter_Cart(Db_DataBase db_dataBase, Context context, List<Datamodel_Cart> datmodel_values, List<Integer> listnumber, Get_Change_proce_final get_change_proce_final)
    {
       this.context=context;
       this.Data=datmodel_values;
       this.get_change_proce_final=get_change_proce_final;
       this.listnumber=listnumber;
       this.db_dataBase=db_dataBase;
    }


    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(context).inflate(R.layout.items_cart,parent,false);
            return new videwholder(view);
    }

    @Override
    public void onBindViewHolder( final videwholder holder, final int position) {
        final Datamodel_Cart datamodel_cart=Data.get(position);
        Glide.with(context).load(urlhome+datamodel_cart.getImage()).into(holder.Im_post);
/*
//         if(offer==1){
//             holder.Rel_discount.setVisibility(View.VISIBLE);
//         }else
//         {
//             holder.Rel_discount.setVisibility(View.GONE);
//         }
*/

//        holder.Im_post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, More_Product.class);
//                intent.putExtra("idproduct",datamodel_cart.getIdproduct());
//                intent.putExtra("offer","0");
//                intent.putExtra("price",datamodel_cart.getPrice());
//                intent.putExtra("price_post",datamodel_cart.getPrice());
//                intent.putExtra("g",datamodel_cart.service);
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation((Activity)context, holder.Im_post, "Image_intent");
//                context.startActivity(intent,options.toBundle());
//            }
//        });

        holder.Tv_title_fa.setText(datamodel_cart.getTitlefa());
        holder.Tv_title_en.setText(datamodel_cart.getTitleen());
        holder.Tv_title_en.setText(datamodel_cart.getTitleen());
        int select=Integer.parseInt(datamodel_cart.getNumber());

        holder.Tv_total_price.setText(datamodel_cart.getTotal_price());
        holder.Tv_final_price.setText(datamodel_cart.getFinal_price());
        holder.Tv_g.setText(datamodel_cart.getService());
        holder.Tv_shop.setText(datamodel_cart.getService());
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listnumber);
        holder.spinner.setAdapter(arrayAdapter);
        if(select==0||select==1){
            holder.spinner.setSelection(0);
        }
        else
        {
            select--;
            holder.spinner.setSelection(select);
        }
        discount=10000;
        holder.Tv_price_discount.setText(String.valueOf(discount));

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n=listnumber.get(position);
               int select_user=listnumber.get(position);
               if(offer==1) {
                    count= (select_user * Integer.parseInt(datamodel_cart.getPrice())-discount*n);

               }else
               {
                   count = select_user * Integer.parseInt(datamodel_cart.getPrice());
               }

               holder.Tv_total_price.setText(String.valueOf(count));
               holder.Tv_final_price.setText(String.valueOf(count));
               db_dataBase.Update_price_product(datamodel_cart.getIdstore(),count,n);
               get_change_proce_final.Strchange_price();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.Tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remorepossion(position,datamodel_cart.getIdstore());

            }
        });

    }

    void remorepossion(int p,String id){
       Data.remove(p);
       notifyItemRemoved(p);
       notifyItemRangeChanged(p,Data.size());
        db_dataBase.Delete_product(id);
        db_dataBase.Get_remove_idstore(id);
        get_change_proce_final.Strchange_price();
        Remove_server(id);

    }

    void Remove_server(String id)
    {
        String url=urlhome+"deletecart.php?token="+ GetToken.gettoken(context)+"&idstore="+id;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(context).add(jsonObjectRequest);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public interface Get_Change_proce_final
    {
        void Strchange_price();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        TextView Tv_title_en,Tv_title_fa,Tv_g,Tv_shop,Tv_total_price,Tv_final_price,Tv_price_discount,Tv_delete;
        Spinner spinner;
        ImageView Im_post;
        RelativeLayout Rel_discount;
        public videwholder(View view) {
            super(view);
            Im_post = view.findViewById(R.id.Im_post);
            Tv_title_en = view.findViewById(R.id.Tv_title_en);
            Tv_title_fa = view.findViewById(R.id.Tv_title_fa);
            Tv_g = view.findViewById(R.id.Tv_g);
            Tv_shop = view.findViewById(R.id.Tv_shop);
            spinner = view.findViewById(R.id.spinner);
            Tv_total_price = view.findViewById(R.id.Tv_total_price);
            Tv_final_price = view.findViewById(R.id.Tv_final_price);
            Rel_discount = view.findViewById(R.id.Rel_discount);
            Tv_price_discount = view.findViewById(R.id.Tv_price_discount);
            Tv_delete = view.findViewById(R.id.Tv_delete);

        }
    }
}
