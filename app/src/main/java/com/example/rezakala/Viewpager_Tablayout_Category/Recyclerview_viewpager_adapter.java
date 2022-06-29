package com.example.rezakala.Viewpager_Tablayout_Category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.R;
import com.example.rezakala.Viewpager_Tablayout_Category.More_Category.Cat_home;

import java.util.List;

public class Recyclerview_viewpager_adapter extends RecyclerView.Adapter<Recyclerview_viewpager_adapter.viewholder> {
    List<Datamodel_category_viewpager> datamodel_category_viewpagers;
    Context context;
    public Recyclerview_viewpager_adapter(Context context, List<Datamodel_category_viewpager> datamodel_category_viewpagers){
        this.context=context;
        this.datamodel_category_viewpagers=datamodel_category_viewpagers;
    }
    @Override
    public viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.items_viewpager_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder( viewholder holder, int position) {
      final  Datamodel_category_viewpager datamodel_category_viewpager=datamodel_category_viewpagers.get(position);
      holder.Tv_title.setText(datamodel_category_viewpager.getName());
        Glide.with(context).load(datamodel_category_viewpager.getIcon()).into(holder.Im_category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Cat_home.class);
                intent.putExtra("name",datamodel_category_viewpager.getName());
                intent.putExtra("idcat",datamodel_category_viewpager.getIdcat());
                intent.putExtra("nameen",datamodel_category_viewpager.getNameen());
                intent.putExtra("subid",datamodel_category_viewpager.getSubid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datamodel_category_viewpagers.size();
    }

    public class viewholder  extends RecyclerView.ViewHolder{
      TextView Tv_title;
      ImageView Im_category;

        public viewholder(View itemView) {
            super(itemView);
            Tv_title=itemView.findViewById(R.id.Tv_title);
            Im_category=itemView.findViewById(R.id.Im_category);

        }
    }
}
