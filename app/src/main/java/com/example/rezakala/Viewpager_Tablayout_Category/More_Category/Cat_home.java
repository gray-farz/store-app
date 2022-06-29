package com.example.rezakala.Viewpager_Tablayout_Category.More_Category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rezakala.R;

import java.util.List;

public class Cat_home extends AppCompatActivity {
//    static Typeface iranianSansFont;
//    NavigationView  navigationView;
//    Custom_product custom_product,Custom_proruct_new;
//    RecyclerView recyclerviewcat;
//    Menu m;
//    TextView Tv_title;
//    Intent intentget;
    Api_Expendable_recyclerview api_expendable_recyclerview;
    //PeopleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_home);
    }
//    void SetupExpandable_recyclerview()
//    {
//        api_expendable_recyclerview=new Api_Expendable_recyclerview(Cat_home.this);
//        api_expendable_recyclerview.Getlistexpandablerecyervliew(new Api_Expendable_recyclerview.getlist() {
//            @Override
//            public void listexpand(List<PeopleListItem> expanditems) {
//                adapter=new PeopleAdapter(Cat_home.this,expanditems);
//                adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
//                recyclerviewcat.setLayoutManager(new LinearLayoutManager(Cat_home.this));
//                recyclerviewcat.setAdapter(adapter);
//            }
//        });
//    }
}