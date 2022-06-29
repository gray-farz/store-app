package com.example.rezakala.Product;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rezakala.R;

import java.util.List;

//import digikala_appsite.com.R;

public class Custom_product extends RelativeLayout {

    TextView Tv_title,Tv_product_all;
    RecyclerView recyclerview_product;
    Adapter_product adapter_product;
    public Custom_product(Context context) {
        super(context);
        Getlistproduct();
    }

    public Custom_product(Context context, AttributeSet attrs) {
        super(context, attrs);
        Getlistproduct();
    }

    public Custom_product(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Getlistproduct();
    }

    public Custom_product(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Getlistproduct();
    }

    public void Getlistproduct()
    {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.product_layout,this,true);
        recyclerview_product=view.findViewById(R.id.recyclerview_product);
        Tv_title=view.findViewById(R.id.Tv_title);
        Tv_product_all=view.findViewById(R.id.Tv_product_all);
    }
    public void getlist(List<Datamodel_listproduct> listproducts){
        recyclerview_product.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        adapter_product=new Adapter_product(getContext(),listproducts);
        recyclerview_product.setAdapter(adapter_product);
    }

    public void Settitle(String title)
    {
        Tv_title.setText(title);
    }
}
