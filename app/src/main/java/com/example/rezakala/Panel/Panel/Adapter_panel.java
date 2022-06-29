package com.example.rezakala.Panel.Panel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rezakala.Panel.Panel.Datamodel.Datamodel_panel;
import com.example.rezakala.R;

import java.util.List;


public class Adapter_panel extends RecyclerView.Adapter<Adapter_panel.videwholder> {
    Context context;
    List<Datamodel_panel> datamodel_cagegory_homes;
    public Adapter_panel(Context context, List<Datamodel_panel> datamodel_cagegory_homes)
    {
       this.context=context;
       this.datamodel_cagegory_homes=datamodel_cagegory_homes;
    }

    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_panel,parent,false);
        return new videwholder(view);
    }

    @Override
    public void onBindViewHolder( videwholder holder, int position) {
        final Datamodel_panel datamodel_cagegory_home=datamodel_cagegory_homes.get(position);
        Glide.with(context).load(datamodel_cagegory_home.getIcon()).into(holder.Im_icon);
        holder.Tv_title.setText(datamodel_cagegory_home.getTitle());
        final int id=datamodel_cagegory_home.getId();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//              switch (id){
//                  case 0 :
//                      Intent in=new Intent(context,Edit_user_panel.class);
//                      context.startActivity(in);
//                      break;
//                  case 1 :
//                      Intent intent_histort=new Intent(context,Activity_Histore_pardakht.class);
//                      context.startActivity(intent_histort);
//                      break;
//                  case 5 :
//                      Intent addin=new Intent(context,Listaddress_useradd.class);
//                      context.startActivity(addin);
//                      break;
//                  case  3 :
//                      Intent favintent=new Intent(context,Fav_user.class);
//                      context.startActivity(favintent);
//                      break;
//                  case 7 :
//                      Intent intent=new Intent(context,Change_password_panel.class);
//                      context.startActivity(intent);
//                      break;
//                  case 8 :
//                      SharedPreferences sharedPreferences=context.getSharedPreferences("info",0);
//                      SharedPreferences.Editor editor=sharedPreferences.edit();
//                      editor.clear();
//                      editor.apply();
//                      Getinfo.Getexit(context);
//                      break;
//
//              }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return datamodel_cagegory_homes.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        ImageView Im_icon;
        TextView Tv_title;
        public videwholder(View view) {
            super(view);
            Im_icon=view.findViewById(R.id.Im_icon);
            Tv_title=view.findViewById(R.id.Tv_title);
        }
    }
}
