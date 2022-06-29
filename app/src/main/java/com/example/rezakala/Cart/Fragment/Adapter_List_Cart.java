package com.example.rezakala.Cart.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.Cart.Datamodel_Cart;
import com.example.rezakala.Config;
import com.example.rezakala.R;

import java.util.List;

public class Adapter_List_Cart extends RecyclerView.Adapter<Adapter_List_Cart.videwholder> implements Config {
    Context context;
    List<Datamodel_Cart> Data;
    public Adapter_List_Cart(Context context, List<Datamodel_Cart> datmodel_values)
    {
       this.context=context;
       this.Data=datmodel_values;
    }

    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(context).inflate(R.layout.items_cartlist,parent,false);
            return new videwholder(view);
    }

    @Override
    public void onBindViewHolder( final videwholder holder, final int position) {
        final Datamodel_Cart datamodel_cart=Data.get(position);
        Glide.with(context).load(datamodel_cart.getImage().replace("http://localhost/digikala",Url_image)).into(holder.Im_store);
        holder.Tv_number.setText("تعداد : "+datamodel_cart.getNumber());
        holder.Tv_price.setText(datamodel_cart.getPrice()+" تومان ");
        holder.Tv_service.setText(" فروشنده : "+datamodel_cart.getService());

        }


    @Override
    public int getItemCount() {
        return Data.size();
    }


    public class videwholder extends RecyclerView.ViewHolder {
        TextView Tv_price,Tv_service,Tv_number;
        ImageView Im_store;
        public videwholder(View view) {
            super(view);
            Im_store = view.findViewById(R.id.Im_store);
            Tv_price = view.findViewById(R.id.Tv_price);
            Tv_service = view.findViewById(R.id.Tv_service);
            Tv_number = view.findViewById(R.id.Tv_number);


        }
    }
}
