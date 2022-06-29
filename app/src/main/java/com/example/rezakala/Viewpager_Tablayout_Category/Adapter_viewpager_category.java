package com.example.rezakala.Viewpager_Tablayout_Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rezakala.R;

import java.util.List;


public class Adapter_viewpager_category extends PagerAdapter {
    Context context;
    List<String> strings;
    RecyclerView recyclerView;
    Recyclerview_viewpager_adapter adapetr;
    private static final String TAG = "Slider_pageradapter";
    public Adapter_viewpager_category(Context context, List<String> strings){
        this.context=context;
        this.strings=strings;

    }
    @Override
    public int getCount() {
        return strings.size();
    }
    @Override
    public boolean isViewFromObject( View view, Object object) {
        return (view==object);
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position)
    {
        final View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_category,null);
        Api_cagegory_viewpager.Getcat(context,
                position, new Api_cagegory_viewpager.Getcat() {
            @Override
            public void Listcat(List<Datamodel_category_viewpager> dcv) {
                recyclerView=view.findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                adapetr=new Recyclerview_viewpager_adapter(context,dcv);
                recyclerView.setAdapter(adapetr);
            }
        });
        container.addView(view);
        return view;
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return strings.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ((ViewPager) container).removeView((View) object);
    }
}
