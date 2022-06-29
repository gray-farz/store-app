package com.example.rezakala.Cart.Fragment;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rezakala.Panel.Panel.Datamodel.Datmodel_address;
import com.example.rezakala.R;

import java.util.List;

public class Adapter_address_fragment extends RecyclerView.Adapter<Adapter_address_fragment.videwholder> {
    Context context;
    int id_rb[];
    List<Datmodel_address> datamodel_cagegory_homes;
    Get_id_address_select get_id_address_select;
    private static final String TAG = "aaa";
    public Adapter_address_fragment(Context context, List<Datmodel_address> datamodel_cagegory_homes, Get_id_address_select get_id_address_select)
    {
       this.context=context;
       this.datamodel_cagegory_homes=datamodel_cagegory_homes;
        id_rb=new int[datamodel_cagegory_homes.size()];
        this.get_id_address_select=get_id_address_select;
    }


    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_address_order,parent,false);
        return new videwholder(view);
    }

    @Override
    public void onBindViewHolder( videwholder holder, int position) {
        final Datmodel_address datamodel_cagegory_home=datamodel_cagegory_homes.get(position);
        int id=View.generateViewId();
        holder.Rb_select.setId(id);
        id_rb[position]=id;
        //Log.d(TAG, "onBindViewHolder in Adapter_address_fragment: "+datamodel_cagegory_home.getAddress());
        holder.Tv_name_user.setText(datamodel_cagegory_home.getName());
        holder.Tv_mobile.setText("شماره تلفن ضروری : "+datamodel_cagegory_home.getMobile());
        holder.Tv_phone_home.setText("شماره تلفن ثابت : "+datamodel_cagegory_home.getPhonehome());
        holder.Tv_address.setText("آدرس : "+datamodel_cagegory_home.getAddress());
        holder.Tv_city.setText(" شهر: ‌"+datamodel_cagegory_home.getCity());
        holder.Tv_ostan_user.setText(" استان : "+datamodel_cagegory_home.getOstan());
        if(position==0){
            holder.Rb_select.setChecked(true);
            get_id_address_select.Get_id_address_select(datamodel_cagegory_home.getIdaddress(),datamodel_cagegory_home.getName(),datamodel_cagegory_home.getAddress(),datamodel_cagegory_home.getMobile());
        }
        holder.Rb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_id_address_select.Get_id_address_select(datamodel_cagegory_home.getIdaddress(),datamodel_cagegory_home.getName(),datamodel_cagegory_home.getAddress(),datamodel_cagegory_home.getMobile());
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

    public interface Get_id_address_select{
        void Get_id_address_select(String idaddress,String name,String address,String phone);
    }

    @Override
    public int getItemCount() {
        return datamodel_cagegory_homes.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        TextView Tv_name_user,Tv_ostan_user,Tv_city,Tv_address,Tv_phone_home,Tv_mobile;
        RadioButton Rb_select;
        public videwholder(View view) {
            super(view);
            Tv_name_user=view.findViewById(R.id.Tv_name_user);
            Tv_ostan_user=view.findViewById(R.id.Tv_ostan_user);
            Tv_city=view.findViewById(R.id.Tv_city);
            Tv_address=view.findViewById(R.id.Tv_address);
            Tv_phone_home=view.findViewById(R.id.Tv_phone_home);
            Tv_mobile=view.findViewById(R.id.Tv_mobile);
            Rb_select=view.findViewById(R.id.Rb_select);
        }
    }
}
