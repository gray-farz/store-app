package com.example.rezakala.Product_offer;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rezakala.Product.Datamodel_listproduct;
import com.example.rezakala.R;

import java.util.List;

//import digikala_appsite.com.Product.Datamodel_listproduct;
//import digikala_appsite.com.R;

public class Custom_product_offer extends RelativeLayout {

    RecyclerView recyclerview_product;
    Adapter_product_offer adapter_product;
    public Custom_product_offer(Context context) {
        super(context);
        Getlistproduct();
    }

    public Custom_product_offer(Context context, AttributeSet attrs) {
        super(context, attrs);
        Getlistproduct();
    }

    public Custom_product_offer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Getlistproduct();
    }

    public Custom_product_offer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Getlistproduct();
    }

    public void Getlistproduct()
    {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.layout_offer,this,true);
        recyclerview_product=view.findViewById(R.id.recyclerview_product);
    }
    public void getlist(List<Datamodel_listproduct> listproducts){
        recyclerview_product.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        adapter_product=new Adapter_product_offer(getContext(),listproducts);
        recyclerview_product.setAdapter(adapter_product);
    }

}
