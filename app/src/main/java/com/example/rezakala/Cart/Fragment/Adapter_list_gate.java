package com.example.rezakala.Cart.Fragment;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.Config;
import com.example.rezakala.R;

import java.util.List;


public class Adapter_list_gate extends
        RecyclerView.Adapter<Adapter_list_gate.videwholder> implements Config {
    Context context;
    int id_rb[];
    Get_possion get_possion;
    List<Datamodel_gate> datamodel_cagegory_homes;
    public Adapter_list_gate(Context context, List<Datamodel_gate> datamodel_cagegory_homes, Get_possion get_possion)
    {
       this.context=context;
       this.datamodel_cagegory_homes=datamodel_cagegory_homes;
        id_rb=new int[datamodel_cagegory_homes.size()];
        this.get_possion=get_possion;
    }

    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.items_list_gate,parent,false);
        return new videwholder(view);
    }

    @Override
    public void onBindViewHolder( videwholder holder, final int position)
    {
        final Datamodel_gate datamodel_cagegory_home=datamodel_cagegory_homes.get(position);
        Glide.with(context).load(datamodel_cagegory_home.getIcon()).into(holder.Im_gate);
        int id=View.generateViewId();
        holder.Rb_select.setId(id);
        holder.Tv_name_gate.setText(datamodel_cagegory_home.getName());
        id_rb[position]=id;
        if(position==0){
            holder.Rb_select.setChecked(true);
            get_possion.Get_p(position);
        }
        holder.Rb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_possion.Get_p(position);
                for (int i = 0; i <id_rb.length ; i++) {
                    RadioButton radioButton=((Activity)context).findViewById(id_rb[i]);
                    if(v.getId()==id_rb[i]){
                        radioButton.setChecked(true);
                    }
                    else
                    {
                        radioButton.setChecked(false);
                    }
                }
            }
        });

    }

    public interface  Get_possion{
        void Get_p(int p);
    }

    @Override
    public int getItemCount()
    {
        return datamodel_cagegory_homes.size();
    }

    public class videwholder extends RecyclerView.ViewHolder
    {
        TextView Tv_name_gate;
        ImageView Im_gate;
        RadioButton Rb_select;
        public videwholder(View view) {
            super(view);
            Im_gate=view.findViewById(R.id.Im_gate);
            Tv_name_gate=view.findViewById(R.id.Tv_name_gate);
            Rb_select=view.findViewById(R.id.Rb_select);
        }
    }
}
