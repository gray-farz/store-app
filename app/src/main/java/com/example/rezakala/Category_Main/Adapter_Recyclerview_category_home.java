package com.example.rezakala.Category_Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rezakala.R;

import java.util.List;


public class Adapter_Recyclerview_category_home extends RecyclerView.Adapter<Adapter_Recyclerview_category_home.videwholder> {
    Context context;
    List<Datamodel_cagegory_home> datamodel_cagegory_homes;
    public Adapter_Recyclerview_category_home(Context context, List<Datamodel_cagegory_home> datamodel_cagegory_homes)
    {
       this.context=context;
       this.datamodel_cagegory_homes=datamodel_cagegory_homes;
    }

    @Override
    public videwholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_category_home,parent,false);
        return new videwholder(view);
    }

    @Override
    public void onBindViewHolder(videwholder holder, int position) {
        final Datamodel_cagegory_home datamodel_cagegory_home=datamodel_cagegory_homes.get(position);
        holder.Btn_category.setText(datamodel_cagegory_home.getTitle());
//       holder.itemView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//           }
//       });
    }

    @Override
    public int getItemCount() {
        return datamodel_cagegory_homes.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        Button Btn_category;
        public videwholder(View view) {
            super(view);
            Btn_category=view.findViewById(R.id.Btn_category);
        }
    }
}
