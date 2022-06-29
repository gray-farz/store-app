package com.example.rezakala.More_Product.Compare;

import android.annotation.SuppressLint;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rezakala.More_Product.Compare.stickyheader.StickyAdapter;
import com.example.rezakala.R;

import java.util.ArrayList;


public class SectionAdapter extends StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder> {
    private static final String TAG = "aaa";
    private ArrayList<Section> sectionArrayList;
    private static final int LAYOUT_HEADER= 0;
    private static final int LAYOUT_CHILD= 1;
 
    public SectionAdapter(ArrayList<Section> sectionArrayList){

       // inflater = LayoutInflater.from(context);
        this.sectionArrayList = sectionArrayList;
    }
 
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
 
        if (viewType == LAYOUT_HEADER ) {
            return new HeaderViewholder(inflater.inflate(R.layout.items_header_1, parent, false));
        }else {
            return new ItemViewHolder(inflater.inflate(R.layout.items_child_1, parent, false));
        }
    }
 
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
 
        if (sectionArrayList.get(position).isHeader()) {
            //Log.d(TAG, "onBindViewHolder header: "+sectionArrayList.get(position).getName());
            ((HeaderViewholder) holder).textView.setText( sectionArrayList.get(position).getName());
        } else {
//            Log.d(TAG, "onBindViewHolder child: "
//                    +sectionArrayList.get(position).GetHeadr_child()
//                    +sectionArrayList.get(position).getinfo()
//                    +sectionArrayList.get(position).getValues_2());

            ((ItemViewHolder) holder).Tv_header_child.setText(sectionArrayList.get(position).GetHeadr_child());
            ((ItemViewHolder) holder).text_value_1.setText
                    (sectionArrayList.get(position).getinfo());
            ((ItemViewHolder) holder).text_value_2.setText
                    (sectionArrayList.get(position).getValues_2());

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(sectionArrayList.get(position).isHeader()) {
            return LAYOUT_HEADER;
        }else
            return LAYOUT_CHILD;
    }
 
    @Override
    public int getItemCount() {
        return sectionArrayList.size();
    }
 
    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        return sectionArrayList.get(itemPosition).sectionPosition();
    }
 
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int headerPosition) {
        ((HeaderViewholder) holder).textView.setText( sectionArrayList.get(headerPosition).getName());
    }
 
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return createViewHolder(parent, LAYOUT_HEADER);
    }
 
    public static class HeaderViewholder extends RecyclerView.ViewHolder {
        TextView textView;
        HeaderViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
 
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView Tv_header_child,text_value_1,text_value_2;
 
        ItemViewHolder(View itemView) {
            super(itemView);
            Tv_header_child = itemView.findViewById(R.id.Tv_header_child);
            text_value_1 = itemView.findViewById(R.id.text_value_1);
            text_value_2 = itemView.findViewById(R.id.text_value_2);
        }
    }
}