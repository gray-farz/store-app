package com.example.rezakala.More_Product.Compare;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.Config;
import com.example.rezakala.Product.Datamodel_listproduct;
import com.example.rezakala.R;

import java.util.List;


public  class Adapter_List_Compare_search extends
        RecyclerView.Adapter<Adapter_List_Compare_search.videwholder>
        implements Config {
    private static final String TAG = "Adapter_List_Product";
    Context context;
    Get_More get_more;
    List<Datamodel_listproduct> datamodel_listproducts;
    public Adapter_List_Compare_search(Context context, List<Datamodel_listproduct> datamodel_listproducts, Get_More get_more)
    {
       this.context=context;
       this.datamodel_listproducts=datamodel_listproducts;
       this.get_more=get_more;

    }

    @Override
    public videwholder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(context).inflate(R.layout.items_compare_search,parent,false);
            return new videwholder(view);

    }

    @Override
    public void onBindViewHolder( videwholder holder,final int position) {
        final Datamodel_listproduct datamodel_listproduct=datamodel_listproducts.get(position);
         holder.Tv_title_en.setText(datamodel_listproduct.getNameen());
         holder.Tv_title_fa.setText(datamodel_listproduct.getName());
         Glide.with(context).load(Url_image+datamodel_listproduct.getPic()).into(holder.Im_post);

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 get_more.getid(datamodel_listproduct.getIdproduct());
             }
         });


    }

    public interface Get_More{
        void getid(String id);
    }

    @Override
    public int getItemCount() {
        return datamodel_listproducts.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        ImageView Im_post;
        TextView Tv_title_en,Tv_title_fa;
        public videwholder(View view) {
            super(view);
            Im_post=view.findViewById(R.id.Im_post);
            Tv_title_en=view.findViewById(R.id.Tv_title_en);
            Tv_title_fa=view.findViewById(R.id.Tv_title_fa);

        }
    }
}
