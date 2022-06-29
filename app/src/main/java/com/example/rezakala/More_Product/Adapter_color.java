package com.example.rezakala.More_Product;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rezakala.R;

import java.util.List;



public class Adapter_color extends RecyclerView.Adapter<Adapter_color.videwholder> {
    Context context;
    List<Datamodel_color> Data;
    int[] colors_id;
    Get_color_change get_color_change;
    private static final String TAG = "aaa";
    public Adapter_color(Context context, List<Datamodel_color> datmodel_values, Get_color_change get_color_change)
    {
       this.context=context;
       this.Data=datmodel_values;
       this.get_color_change=get_color_change;
       colors_id=new int[datmodel_values.size()];

    }


    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(context).inflate(R.layout.items_color_more,parent,false);
            return new videwholder(view);

    }

    @Override
    public void onBindViewHolder(final videwholder holder, @SuppressLint("RecyclerView") final int position) {
        int id=View.generateViewId();
        holder.Rel_body.setId(id);
        colors_id[position]=id;
        final Datamodel_color data=Data.get(position);
        holder.Tv_title.setText(data.getName_color());
        if(position==0){
            holder.Rel_body.setBackgroundResource(R.drawable.border_colors_active);
        }
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setCornerRadius(100);
        //Log.d(TAG, "onBindViewHolder: "+data.getColor());
        //gradientDrawable.setStroke(2,context.getColor(R.color.black));
        gradientDrawable.setColor(Color.parseColor(data.getColor()));
        holder.view1.setBackground(gradientDrawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  get_color_change.Changecolor(data.getIdcolor(),position);
                for (int i = 0; i <colors_id.length ; i++)
                {
                    RelativeLayout relativeLayout=((Activity)context).
                            findViewById(colors_id[i]);
                    relativeLayout.setBackgroundResource(R.drawable.border_colors_noactive);
                    v.setBackgroundResource(R.drawable.border_colors_active);
                }
            }
        });

        }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public interface  Get_color_change{
        void Changecolor(String idcolor,int possion);
    }

    public class videwholder extends RecyclerView.ViewHolder {
        TextView Tv_title;
        View view1;
        RelativeLayout Rel_body;
        public videwholder(View view) {
            super(view);
                Tv_title = view.findViewById(R.id.Tv_title);
                Rel_body = view.findViewById(R.id.Rel_body);
                view1 = view.findViewById(R.id.view1);

        }
    }
}
