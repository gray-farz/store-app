package com.example.rezakala.Product_offer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.Action.Desimal_format_Interger;
import com.example.rezakala.Product.Datamodel_listproduct;
import com.example.rezakala.R;

import java.util.List;

//import digikala_appsite.com.ACtion.Desimal_format_Interger;
//import digikala_appsite.com.More_Product.More_Product;
//import digikala_appsite.com.Product.Datamodel_listproduct;
//import digikala_appsite.com.R;

public class Adapter_product_offer extends RecyclerView.Adapter<Adapter_product_offer.videwholder> {
    Context context;
    List<Datamodel_listproduct> datamodel_cagegory_homes;
    public Adapter_product_offer(Context context, List<Datamodel_listproduct> datamodel_cagegory_homes)
    {
       this.context=context;
       this.datamodel_cagegory_homes=datamodel_cagegory_homes;
    }

    @Override
    public videwholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_offer,parent,false);
        return new videwholder(view);
    }

    @Override
    public void onBindViewHolder(videwholder holder, int position) {
        final Datamodel_listproduct datamodel_cagegory_home=datamodel_cagegory_homes.get(position);
        Glide.with(context).load(datamodel_cagegory_home.getPic().replace("localhost","192.168.43.54")).into(holder.Image_post);
        holder.Tv_price.setText(Desimal_format_Interger.GetformatInteger(datamodel_cagegory_home.getPrice_sale())+" تومان ");
        holder.Tv_title.setText(datamodel_cagegory_home.getName());
        holder.Tv_offer.setText(Desimal_format_Interger.GetformatInteger(datamodel_cagegory_home.getOffer())+" تومان ");
        holder.Tv_price.setPaintFlags(holder.Tv_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context,More_Product.class);
//                intent.putExtra("idproduct",datamodel_cagegory_home.getIdproduct());
//                intent.putExtra("offer","1");
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datamodel_cagegory_homes.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        ImageView Image_post;
        TextView Tv_title,Tv_offer;
        TextView Tv_price;
        public videwholder(View view) {
            super(view);
            Image_post=view.findViewById(R.id.Image_post);
            Tv_title=view.findViewById(R.id.Tv_title);
            Tv_price=view.findViewById(R.id.Tv_price);
            Tv_offer=view.findViewById(R.id.Tv_offer);
        }
    }
}
