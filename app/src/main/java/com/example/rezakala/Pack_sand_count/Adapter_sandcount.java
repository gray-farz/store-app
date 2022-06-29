package com.example.rezakala.Pack_sand_count;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.R;

import java.util.List;



public class Adapter_sandcount extends RecyclerView.Adapter<Adapter_sandcount.videwholder> {
    Context context;
    public final static int view_type_header=0;
    public final static  int view_type_spandcount=1;
    List<Datamodel_sandcount> datamodel_cagegory_homes;
    public Adapter_sandcount(Context context, List<Datamodel_sandcount> datamodel_cagegory_homes)
    {
       this.context=context;
       this.datamodel_cagegory_homes=datamodel_cagegory_homes;
    }


    @Override
    public videwholder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case view_type_header :
            return new videwholder(LayoutInflater.from(context).inflate(R.layout.type_header,parent,false));
            case view_type_spandcount :
                return new videwholder(LayoutInflater.from(context).inflate(R.layout.type_span,parent,false));
                default:
                    return null;
        }

    }

    @Override
    public void onBindViewHolder(videwholder holder, int position) {
        final Datamodel_sandcount datamodel_cagegory_home=datamodel_cagegory_homes.get(position);
        Glide.with(context).load(datamodel_cagegory_home.getPic().replace("localhost","192.168.43.54")).into(holder.Image_span_count);
    }

    @Override
    public int getItemViewType(int position) {
       if(position==0)
           return 0;
           return 1;
    }

    @Override
    public int getItemCount() {
        return datamodel_cagegory_homes.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        ImageView Image_span_count;
        public videwholder(View view) {
            super(view);
            Image_span_count=view.findViewById(R.id.Image_span_count);
        }
    }
}
