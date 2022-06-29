package com.example.rezakala.Packslider;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rezakala.R;

import java.util.List;

public class Slider_pageradapter extends PagerAdapter {
    Context context;
    List<String> strings;
    ImageView Image_slider;
    int[] idview;
    Activity activity;
    private static final String TAG = "Slider_pageradapter";
    public Slider_pageradapter(Activity activity, Context context, List<String> strings, int[] idview){
        Log.d(TAG, "Slider_pageradapter: ");
        this.activity=activity;
       this.context=context;
       this.strings=strings;
       this.idview=idview;



    }
    @Override
    public int getCount() {
        return strings.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate
                (R.layout.slider_latyout,null);
        Image_slider=view.findViewById(R.id.Image_slider);
        Log.d(TAG, "instantiateItem: "+strings.get(position));
        Glide.with(context).load(strings.get(position).
                replace("localhost","192.168.43.54")).
                apply(new RequestOptions().
                        placeholder(R.drawable.logo_sp).
                        error(R.drawable.logo_sp)).into(Image_slider);

            for (int i = 0; i < idview.length; i++) {
                View viewslider = (activity).findViewById(idview[i]);
                if (viewslider != null) {
                    if (i == position) {
                        viewslider.setBackgroundResource(R.drawable.shape_slider_active);
                    } else {
                        viewslider.setBackgroundResource(R.drawable.shape_slider_noactive);
                    }
                }

            }

        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
