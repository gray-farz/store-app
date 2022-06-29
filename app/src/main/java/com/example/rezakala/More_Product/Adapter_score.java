package com.example.rezakala.More_Product;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rezakala.R;

import java.util.List;

public class Adapter_score extends RecyclerView.Adapter<Adapter_score.videwholder> {
    Context context;
    List<Datamodel_score> Data;
    private static final String TAG = "Adapter_score";
    int count;
    public Adapter_score(int count, Context context,
                         List<Datamodel_score> Data)
    {
       this.context=context;
       this.Data=Data;
       this.count=count;
    }

    @Override
    public videwholder onCreateViewHolder( ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).
                    inflate(R.layout.items_score,parent,false);
            return new videwholder(view);
    }

    @Override
    public void onBindViewHolder( final videwholder holder,
                                  final int position) {
         Datamodel_score datamodel_score=Data.get(position);
         holder.Tv_title.setText(datamodel_score.getTitle());
        String color_default="#ECF0F1";
        String color_green="#27AE60";
        String sp=datamodel_score.getScore();
        String[] sp1=sp.split(",");
        int index1=Integer.parseInt(sp1[0]);
        int index2=Integer.parseInt(sp1[1]);
        int k=(index2*count)/10;

        /// give color default to all
        for (int i = 0; i <Data.size() ; i++)
        {
              switch (i){
                  case 0 :
                      LinearLayout.LayoutParams p1=new
                              LinearLayout.LayoutParams(count,-2);
                      View view1=new View(context);
                      view1.setLayoutParams(p1);
                      view1.setBackgroundColor
                              (Color.parseColor(color_default));
                      holder.view_1.addView(view1);
                      break;

                  case 1 :
                      LinearLayout.LayoutParams p2=new
                              LinearLayout.LayoutParams(count,-2);
                      View view2=new View(context);
                      view2.setLayoutParams(p2);
                      view2.setBackgroundColor(Color.parseColor(color_default));
                      holder.view_2.addView(view2);
                      break;
                  case 2 :
                      LinearLayout.LayoutParams p3=new
                              LinearLayout.LayoutParams(count,-2);
                      View view3=new View(context);
                      view3.setLayoutParams(p3);
                      view3.setBackgroundColor(Color.parseColor(color_default));
                      holder.view_3.addView(view3);
                      break;

                  case 3 :
                      LinearLayout.LayoutParams p4=new LinearLayout.LayoutParams(count,-2);
                      View view4=new View(context);
                      view4.setLayoutParams(p4);
                      view4.setBackgroundColor(Color.parseColor(color_default));
                      holder.view_4.addView(view4);
                      break;

                  case 4 :
                      LinearLayout.LayoutParams p5=new LinearLayout.LayoutParams(count,-2);
                      View view5=new View(context);
                      view5.setLayoutParams(p5);
                      view5.setBackgroundColor(Color.parseColor(color_default));
                      holder.view_5.addView(view5);
                      break;
              }
           }

        /// give green color to integer part of number
        for (int i = 0; i <index1 ; i++)
        {
            switch (i){
                case 0 :
                    LinearLayout.LayoutParams p1=new
                            LinearLayout.LayoutParams(count,-2);
                    View view1=new View(context);
                    view1.setLayoutParams(p1);
                    view1.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_1.addView(view1);
                    break;

                case 1 :
                    LinearLayout.LayoutParams p2=new LinearLayout.LayoutParams(count,-2);
                    View view2=new View(context);
                    view2.setLayoutParams(p2);
                    view2.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_2.addView(view2);
                    break;
                case 2 :
                    LinearLayout.LayoutParams p3=new LinearLayout.LayoutParams(count,-2);
                    View view3=new View(context);
                    view3.setLayoutParams(p3);
                    view3.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_3.addView(view3);
                    break;

                case 3 :
                    LinearLayout.LayoutParams p4=new LinearLayout.LayoutParams(count,-2);
                    View view4=new View(context);
                    view4.setLayoutParams(p4);
                    view4.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_4.addView(view4);
                    break;

                case 4 :
                    LinearLayout.LayoutParams p5=new LinearLayout.LayoutParams(count,-2);
                    View view5=new View(context);
                    view5.setLayoutParams(p5);
                    view5.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_5.addView(view5);
                    break;
            }
        }

        switch (index1){
            case 0:
                break;

            case 1:
                 if(index2!=0)
                 {
                     LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(k, -2);
                     View view1 = new View(context);
                     view1.setLayoutParams(p1);
                     view1.setBackgroundColor(Color.parseColor(color_green));
                     holder.view_2.addView(view1);
                 }
                break;


            case 2:
                if(index2!=0) {
                    LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(k, -2);
                    View view1 = new View(context);
                    view1.setLayoutParams(p1);
                    view1.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_3.addView(view1);
                }
                break;

            case 3:
                if(index2!=0) {
                    LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(k, -2);
                    View view1 = new View(context);
                    view1.setLayoutParams(p1);
                    view1.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_4.addView(view1);
                }
                break;

            case 4:
                if(index2!=0) {
                    LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(k, -2);
                    View view1 = new View(context);
                    view1.setLayoutParams(p1);
                    view1.setBackgroundColor(Color.parseColor(color_green));
                    holder.view_5.addView(view1);
                }
                break;
        }

        }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class videwholder extends RecyclerView.ViewHolder {
        TextView Tv_title;
        RelativeLayout view_1,view_2,view_3,view_4,view_5;
        public videwholder(View view) {
            super(view);
                Tv_title = view.findViewById(R.id.Tv_title);
            view_1 = view.findViewById(R.id.view_1);
            view_2 = view.findViewById(R.id.view_2);
            view_3 = view.findViewById(R.id.view_3);
            view_4 = view.findViewById(R.id.view_4);
            view_5 = view.findViewById(R.id.view_5);

        }
    }
}
