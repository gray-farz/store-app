package com.example.rezakala.Panel.Panel;

import android.content.Context;

import androidx.core.content.res.ResourcesCompat;

import com.example.rezakala.Panel.Panel.Datamodel.Datamodel_panel;
import com.example.rezakala.R;

import java.util.ArrayList;
import java.util.List;


public class Api_feck_panel {

    public static List<Datamodel_panel> Panelapifeck(Context context){
        List<Datamodel_panel> datamodel_panels=new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
             Datamodel_panel datamodel_panel=new Datamodel_panel();

            switch (i){
                case 0 :
                    datamodel_panel.setId(0);
                    datamodel_panel.setTitle("ویرایش اطلاعات");
                    datamodel_panel.setIcon(
                            ResourcesCompat.getDrawable(context.getResources(),
                                    R.drawable.ic_black_mic,null));
                    break;

                case 1 :
                    datamodel_panel.setId(1);
                    datamodel_panel.setTitle("سفارش های من");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;

                case 2 :
                    datamodel_panel.setId(2);
                    datamodel_panel.setTitle("دیجی بن");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;

                case 3 :
                    datamodel_panel.setId(3);
                    datamodel_panel.setTitle("لیست مورد علاقه");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;

                case 4 :
                    datamodel_panel.setId(4);
                    datamodel_panel.setTitle("پیام ها");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;
                case 5 :
                    datamodel_panel.setId(5);
                    datamodel_panel.setTitle("آدرس های من");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;
                case 6 :
                    datamodel_panel.setId(6);
                    datamodel_panel.setTitle("شماره کارت بانکی");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;
                case 7 :
                    datamodel_panel.setId(7);
                    datamodel_panel.setTitle("تغییر رمز عبور");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;
                case 8 :
                    datamodel_panel.setId(8);
                    datamodel_panel.setTitle("خروج");
                    datamodel_panel.setIcon(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_black_mic,null));
                    break;
            }

            datamodel_panels.add(datamodel_panel);

        }

      return datamodel_panels;
    }
}
